// first line

package app.config.db;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import app.helpers.calendar.Event;
import app.helpers.mail.MailAccount;
import app.helpers.mail.Oauth.OAuth;
import app.helpers.mail.PMA.PswdMailAccount;

public class DbHandler {
    private DbHandler(){}

    private static Connection connection;

    private static final String AND = "\nAND ";

    public static Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:sqlite:" + DbConfig.Get_Db_Path());
        }
        return connection;
    }

    public static String cAnd (int count, String q){
        if (count > 1){
            return q+AND;
        }
        return q;
    }

    public class Events{
        private Events(){}

        private final static String CREA =  """
            INSERT INTO DATA (evento, obj, quando, done, creato)
            VALUES (?, ?, ?, ?, ?);
            """;
            private final static String READ = """
                SELECT id, evento, obj, quando, done, creato
                FROM DATA;
            """;
            private final static String UPDATE = """
                UPDATE DATA
                SET evento = ?, obj = ?, quando = ?, done = ?, creato = ?
                WHERE id = ?
            """;
            private final static String DELETE = """
                DELETE FROM DATA
                WHERE id = ?;
            """;
            private final static String FIND_SINGLE ="""
                    SELECT id, evento, obj, quando, done, creato
                    FROM data
                    WHERE 
                    """;
            private static final String FIND = """
                    SELECT tipo
                    FROM TYPE
                    WHERE id = ?
                    """;
            private final static String EVENTO = "evento = ?";
            private final static String OBJ = "obj = ?";
            private final static String QUANDO = "quando = ?";
            private static final String DONE = "done = ?";
            private static final String CREATO = "creato = ?";

    public static boolean Create(Event gioie){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(CREA);
            String a = null;
            if (gioie.GetWhen() != null){
                a = gioie.GetWhen().toString();
            }
            if (gioie.event() != null){
                bs.setInt(1, gioie.event());
            } else{
                bs.setInt(1, 2);
            }
            bs.setString(2, gioie.GetObj());
            bs.setString(3, a);
            if(gioie.GetDone() == null){
            bs.setBoolean(4, false);
            } else {
                bs.setBoolean(4, gioie.GetDone());
            }
            bs.setString(5, gioie.GetCreated().toString());
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Event> Read (){
        ArrayList<Event> evee = new ArrayList<>();
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(READ);
            ResultSet rawr = bs.executeQuery();
            while (rawr.next()){
                int id = rawr.getInt("id");
                int event = rawr.getInt("evento");
                String obj = rawr.getString("obj");
                String quando = rawr.getString("quando");
                LocalDate when = null;
                if (quando != null){
                    when = LocalDate.parse(quando);
                }
                boolean done = rawr.getBoolean("done");
                LocalDate created = LocalDate.parse(rawr.getString("creato"));
                Event gioia = new Event(id, event, obj, when, done, created);
                evee.add(gioia);
            }
        } catch (Exception a){
            a.printStackTrace();
        }
        return evee;
    }

    public static ArrayList<Event> Find(Event e){
        ArrayList<Event> res = new ArrayList<>();
        try {
            String query = FIND_SINGLE;
            ArrayList<Object> values = new ArrayList<>();
            Connection c = getConnection();
            int count = 0;
            if (e.event() != null && e.event() <4 && e.event() > 0){
            count +=1;
            query = cAnd(count, query) + EVENTO;
            values.add(e.event());
            }
            if(e.GetObj() != null){
                count +=1;
                query = cAnd(count, query) + OBJ;
                values.add(e.GetObj());
            }
            if(e.GetWhen() != null){
                count +=1;
                query = cAnd(count, query) + QUANDO;
                values.add(e.GetWhen().toString());
            }
            if(e.GetCreated() != null){
                count +=1;
                query = cAnd(count, query) + CREATO;
                values.add(e.GetCreated().toString());
            }
            if (e.GetDone() != null){
            count +=1;
            query = cAnd(count, query) + DONE;
            values.add(e.GetDone());
            }
            PreparedStatement bs = c.prepareStatement(query);
            for (int i = 0; i < values.size(); i++){
                bs.setObject(i+1, values.get(i));
            }
            ResultSet rawr = bs.executeQuery();
            while (rawr.next()){
                int id = rawr.getInt("id");
                int event = rawr.getInt("evento");
                String obj = rawr.getString("obj");
                String quando = rawr.getString("quando");
                LocalDate when = null;
                if (quando != null){
                    when = LocalDate.parse(quando);
                }
                boolean done = rawr.getBoolean("done");
                LocalDate created = LocalDate.parse(rawr.getString("creato"));
                Event gioia = new Event(id, event, obj, when, done, created);
                res.add(gioia);
            }
        } catch (SQLException a){
            a.printStackTrace();
        }
        return res;
    }

    public static boolean Update(Event gioie){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(UPDATE);
            String a = null;
            if (gioie.GetWhen() != null){
                a = gioie.GetWhen().toString();
            }
            bs.setInt(1, gioie.event());
            bs.setString(2, gioie.GetObj());
            bs.setString(3, a);
            if(gioie.GetDone() == null){
            bs.setBoolean(4, false);
            } else {
                bs.setBoolean(4, gioie.GetDone());
            }
            bs.setString(5, gioie.GetCreated().toString());
            bs.setInt(6, gioie.GetId());
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Kill(int id){
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(DELETE);
            bs.setInt(1, id);
            return bs.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static String Tipo(int id){
            String res = "";
        try {
            Connection c = getConnection();
            PreparedStatement bs = c.prepareStatement(FIND);
            bs.setInt(1, id);
            ResultSet rawr = bs.executeQuery();
            if(rawr.next()){
                return rawr.getString("tipo");
            }
            return res;
        } catch (SQLException e){
            e.printStackTrace();
            return res;
        }
    }

    }

    public static class Mail {

        private Mail() {}

        private static final String VALID_MAIL = """
            SELECT *
            FROM MAIL_ACCOUNT
            WHERE address IS NOT NULL
            AND provider IS NOT NULL
            AND auth_method IS NOT NULL
            """;
        private static final String CREATE = """
            INSERT INTO MAIL_ACCOUNT (provider, service, address, account_identifier, username, auth_method, credential_reference, access_token, refresh_token, expires_at, scopes)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        private static final String READ = """
            SELECT *
            FROM MAIL_ACCOUNT
            WHERE id = ?
            """;
        private static final String READ_ALL = """
            SELECT *
            FROM MAIL_ACCOUNT
            """;
        private static final String UPDATE = """
            UPDATE MAIL_ACCOUNT
            SET provider = ?,
            service = ?,
            address = ?,
            account_identifier = ?,
            username = ?,
            auth_method = ?,
            credential_reference = ?,
            access_token = ?,
            refresh_token = ?,
            expires_at = ?,
            scopes = ?
            WHERE id = ?
            """;
        private static final String DELETE = """
            DELETE FROM MAIL_ACCOUNT
            WHERE id = ?
            """;

            public static int create(MailAccount account) throws SQLException {
                String accessToken = null;
                String refreshToken = null;
                String expiresAt = null;
                String scopes = null;
                if (account instanceof OAuth oauth) {
                    accessToken = oauth.getAT();
                    refreshToken = oauth.getRT();
                    expiresAt = oauth.getEAT();
                    scopes = oauth.getS();
                }
                try (PreparedStatement ps = DbHandler.getConnection()
                        .prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, account.getProvider());
                    ps.setString(2, account.getService());
                    ps.setString(3, account.getAddress());
                    ps.setString(4, account.getAI());
                    ps.setString(5, account.getUsername());
                    ps.setString(6, account.getAM());
                    ps.setString(7, account.getCR());
                    ps.setString(8, accessToken);
                    ps.setString(9, refreshToken);
                    ps.setString(10, expiresAt);
                    ps.setString(11, scopes);
                    ps.executeUpdate();
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            return rs.getInt(1);
                        }
                    }
                }
                return -1;
            }

        public static MailAccount read(int id) throws SQLException {
            try (PreparedStatement ps = DbHandler.getConnection().prepareStatement(READ)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return fromResultSet(rs);
                    }
                }
            }
            return null;
        }

        public static ArrayList<MailAccount> readAll() throws SQLException {
            ArrayList<MailAccount> accounts = new ArrayList<>();
            try (PreparedStatement ps = DbHandler.getConnection().prepareStatement(READ_ALL);
                ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MailAccount account = fromResultSet(rs);
                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }
            return accounts;
        }

        public static boolean update(MailAccount account) throws SQLException {
            String accessToken = null;
            String refreshToken = null;
            String expiresAt = null;
            String scopes = null;
            if (account instanceof OAuth oauth) {
                accessToken = oauth.getAT();
                refreshToken = oauth.getRT();
                expiresAt = oauth.getEAT();
                scopes = oauth.getS();
            }
            try (PreparedStatement ps = DbHandler.getConnection().prepareStatement(UPDATE)) {
                ps.setString(1, account.getProvider());
                ps.setString(2, account.getService());
                ps.setString(3, account.getAddress());
                ps.setString(4, account.getAI());
                ps.setString(5, account.getUsername());
                ps.setString(6, account.getAM());
                ps.setString(7, account.getCR());
                ps.setString(8, accessToken);
                ps.setString(9, refreshToken);
                ps.setString(10, expiresAt);
                ps.setString(11, scopes);
                ps.setInt(12, account.getId());
                return ps.executeUpdate() == 1;
            }
        }

        public static boolean delete(int id) throws SQLException {
            try (PreparedStatement ps = DbHandler.getConnection().prepareStatement(DELETE)) {
                ps.setInt(1, id);
                return ps.executeUpdate() == 1;
            }
        }

        public static ArrayList<MailAccount> usable() throws SQLException {
            ArrayList<MailAccount> accounts = new ArrayList<>();
            try (PreparedStatement ps = DbHandler.getConnection().prepareStatement(VALID_MAIL);
                ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MailAccount account = fromResultSet(rs);
                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }
            return accounts;
        }

        private static MailAccount fromResultSet(ResultSet rs) throws SQLException {
            int id = rs.getInt("id");
            String provider = rs.getString("provider");
            String service = rs.getString("service");
            String address = rs.getString("address");
            String accountIdentifier = rs.getString("account_identifier");
            String username = rs.getString("username");
            String authMethod = rs.getString("auth_method");
            String credentialReference = rs.getString("credential_reference");
            if ("OAUTH2".equalsIgnoreCase(authMethod)) {
                return new OAuth(id, provider, address, accountIdentifier, service, authMethod, username, credentialReference, rs.getString("access_token"), rs.getString("refresh_token"), rs.getString("expires_at"), rs.getString("scopes"));
            }
            if ("APP_PASSWORD".equalsIgnoreCase(authMethod)) {
                return new PswdMailAccount(id, provider, address, accountIdentifier, service, authMethod, username, credentialReference);
            }
            return null;
        }
    }

    public static final class Vault{
        private Vault(){}
        private static final String CREATE = """
                INSERT INTO VAULT (id, salt, verifier, iterations)
                VALUES (1, ?, ?, ?);
                """;;
        private static final String READ = """
                SELECT salt, verifier, iterations
                FROM VAULT
                WHERE id = 1;
                """;
        private static final String UPDATE = """
                UPDATE VAULT
                SET salt = ?, verifier = ?, iterations = ?
                WHERE id = 1;
                """;
        private static final String EXISTS = """
                SELECT 1
                FROM VAULT
                WHERE id = 1;
                """;

        public record Config(byte[] salt, byte[] verifier, int iterations){}

        public static boolean isConfigured() throws SQLException {
            try(
                PreparedStatement bs = getConnection().prepareStatement(EXISTS);
                ResultSet rawr = bs.executeQuery();
            ){
                return rawr.next();
            }
        }

        public static boolean create(byte[] salt, byte[] verifier, int iterations) throws SQLException {
            try(PreparedStatement bs = getConnection().prepareStatement(CREATE)){
                bs.setBytes(1, salt);
                bs.setBytes(2, verifier);
                bs.setInt(3, iterations);
                return bs.executeUpdate() == 1;
            }
        }

        public static Config read() throws SQLException{
            try(
                PreparedStatement bs = getConnection().prepareStatement(READ);
                ResultSet rawr = bs.executeQuery();
            ){
                if (!rawr.next()){
                    return null;
                }
                return new Config(rawr.getBytes("salt"), rawr.getBytes("verifier"), rawr.getInt("iterations"));
            }
        }

        public static boolean update(byte[] salt, byte[] verifier, int iterations) throws SQLException{
            try(
                PreparedStatement bs = getConnection().prepareStatement(UPDATE);
            ){
                bs.setBytes(1, salt);
                bs.setBytes(2, verifier);
                bs.setInt(3, iterations);
                return bs.executeUpdate() == 1;
            }
        }
    }
}

// last line