package app.config.db;

import app.helpers.events.Event;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class DbStressTest {
    private static final int MAX_LIVE_EVENTS = 200;
    private static final long DEFAULT_SEED = 0x5EEDC0DEL;
    private static final String[] TEXT_PARTS = {
        "", "ordinary text", "don't split", "' OR 1=1 --", "%_ wildcard",
        "line one\nline two", "caffè ☕", "emoji 🚀", "semi;colon"
    };

    private DbStressTest() {}

    public static void main(String[] args) throws Exception {
        if (args.length > 2) {
            throw new IllegalArgumentException(
                    "Usage: DbStressTest [operation-count] [seed]");
        }

        int operationCount = args.length > 0 ? Integer.parseInt(args[0]) : 10_000;
        long seed = args.length > 1 ? Long.parseLong(args[1]) : DEFAULT_SEED;
        if (operationCount < 1) {
            throw new IllegalArgumentException("operation-count must be positive");
        }

        if (!DbInit.init() || !DbConfig.DbValid()) {
            throw new IllegalStateException("The application database could not be initialized");
        }

        Connection connection = DbHandler.getConnection();
        boolean originalAutoCommit = connection.getAutoCommit();
        Savepoint savepoint = null;
        if (originalAutoCommit) {
            connection.setAutoCommit(false);
        } else {
            savepoint = connection.setSavepoint();
        }

        try {
            runFuzz(connection, operationCount, seed);
            System.out.printf(
                    "Database stress test passed: %,d operations (seed %d).%n",
                    operationCount, seed);
        } finally {
            if (originalAutoCommit) {
                connection.rollback();
                connection.setAutoCommit(true);
            } else {
                connection.rollback(savepoint);
            }
        }
        System.out.println("All stress-test changes were rolled back.");
    }

    private static void runFuzz(Connection connection, int operationCount, long seed)
            throws SQLException {
        Random random = new Random(seed);
        List<ExpectedEvent> liveEvents = new ArrayList<>();
        int nextToken = 0;

        for (int operation = 0; operation < operationCount; operation++) {
            int choice = random.nextInt(100);
            if (liveEvents.isEmpty()
                    || (liveEvents.size() < MAX_LIVE_EVENTS && choice < 32)) {
                ExpectedEvent created = randomEvent(random, seed, nextToken++);
                DbHandler.Create(created.toEvent());
                created = assertStored(created, seed, operation);
                liveEvents.add(created);
            } else if (choice < 56) {
                ExpectedEvent selected = liveEvents.get(random.nextInt(liveEvents.size()));
                assertStored(selected, seed, operation);
                assertFilteredFind(selected, random, seed, operation);
            } else if (choice < 81) {
                int index = random.nextInt(liveEvents.size());
                ExpectedEvent previous = liveEvents.get(index);
                ExpectedEvent updated = randomEvent(random, seed, nextToken++)
                        .withId(previous.id());
                DbHandler.Update(updated.toEvent());
                assertNoMatch(previous.obj(), seed, operation);
                assertStored(updated, seed, operation);
                liveEvents.set(index, updated);
            } else {
                int index = random.nextInt(liveEvents.size());
                ExpectedEvent removed = liveEvents.remove(index);
                DbHandler.Kill(removed.id());
                assertNoMatch(removed.obj(), seed, operation);
            }

            if (operation % 100 == 99) {
                assertReadable(liveEvents, seed, operation);
            }
        }

        for (int operation = 0; operation < liveEvents.size(); operation++) {
            assertStored(liveEvents.get(operation), seed, operationCount + operation);
        }
        assertReadable(liveEvents, seed, operationCount);

        try (Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery("PRAGMA integrity_check")) {
            if (!result.next()) {
                throw failure(seed, operationCount, "SQLite integrity_check returned no result");
            }
            String detail = result.getString(1);
            if (!"ok".equalsIgnoreCase(detail)) {
                throw failure(seed, operationCount, "SQLite integrity_check failed: " + detail);
            }
        }
    }

    private static ExpectedEvent randomEvent(Random random, long seed, int token) {
        String uniqueToken = "__dbstress_" + Long.toUnsignedString(seed, 36) + "_" + token + "__";
        String obj = randomText(random) + uniqueToken;
        LocalDate when = random.nextInt(4) == 0 ? null : randomDate(random);
        Boolean done = random.nextInt(5) == 0 ? null : random.nextBoolean();
        return new ExpectedEvent(
                -1,
                random.nextInt(3) + 1,
                obj,
                when,
                Boolean.TRUE.equals(done),
                randomDate(random));
    }

    private static String randomText(Random random) {
        StringBuilder text = new StringBuilder();
        int pieces = random.nextInt(8);
        for (int i = 0; i < pieces; i++) {
            text.append(TEXT_PARTS[random.nextInt(TEXT_PARTS.length)]);
            if (random.nextBoolean()) {
                text.append((char) ('a' + random.nextInt(26)));
            }
        }
        return text.toString();
    }

    private static LocalDate randomDate(Random random) {
        return LocalDate.of(1990 + random.nextInt(60), 1, 1)
                .plusDays(random.nextInt(365));
    }

    private static ExpectedEvent assertStored(ExpectedEvent expected, long seed, int operation) {
        List<Event> found = DbHandler.Find(new Event(
                0, 0, expected.obj(), null, null, null));
        if (found.size() != 1) {
            throw failure(seed, operation, "expected one row for " + expected.obj()
                    + ", found " + found.size());
        }

        Event actual = found.get(0);
        if ((expected.id() >= 0 && actual.GetId() != expected.id())
                || !matches(expected, actual)) {
            throw failure(seed, operation, "row did not match expected values for "
                    + expected.obj());
        }
        return expected.withId(actual.GetId());
    }

    private static void assertFilteredFind(
            ExpectedEvent expected, Random random, long seed, int operation) {
        boolean includeEvent = random.nextBoolean();
        boolean includeWhen = random.nextBoolean();
        boolean includeDone = random.nextBoolean();
        boolean includeCreated = random.nextBoolean();
        Event filter = new Event(
                0,
                includeEvent ? expected.event() : 0,
                expected.obj(),
                includeWhen ? expected.when() : null,
                includeDone ? expected.done() : null,
                includeCreated ? expected.created() : null);
        List<Event> found = DbHandler.Find(filter);
        if (found.size() != 1 || found.get(0).GetId() != expected.id()) {
            throw failure(seed, operation, "filtered find returned "
                    + found.size() + " rows for " + expected.obj());
        }
    }

    private static void assertNoMatch(String obj, long seed, int operation) {
        if (!DbHandler.Find(new Event(0, 0, obj, null, null, null)).isEmpty()) {
            throw failure(seed, operation, "deleted/replaced row was still found: " + obj);
        }
    }

    private static void assertReadable(
            List<ExpectedEvent> expectedEvents, long seed, int operation) {
        List<Event> rows = DbHandler.Read();
        for (ExpectedEvent expected : expectedEvents) {
            boolean found = false;
            for (Event actual : rows) {
                if (actual.GetId() == expected.id() && matches(expected, actual)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw failure(seed, operation, "Read did not return expected row "
                        + expected.obj());
            }
        }
    }

    private static boolean matches(ExpectedEvent expected, Event actual) {
        return actual.event() == expected.event()
                && expected.obj().equals(actual.GetObj())
                && Objects.equals(expected.when(), actual.GetWhen())
                && Boolean.valueOf(expected.done()).equals(actual.GetDone())
                && expected.created().equals(actual.GetCreated());
    }

    private static AssertionError failure(long seed, int operation, String message) {
        return new AssertionError("seed " + seed + ", operation " + operation + ": " + message);
    }

    private record ExpectedEvent(
            int id,
            int event,
            String obj,
            LocalDate when,
            boolean done,
            LocalDate created) {
        private Event toEvent() {
            return new Event(id, event, obj, when, done, created);
        }

        private ExpectedEvent withId(int newId) {
            return new ExpectedEvent(newId, event, obj, when, done, created);
        }
    }
}