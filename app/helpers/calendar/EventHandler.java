// first line

package app.helpers.calendar;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import app.config.db.DbHandler;

public class EventHandler {
    private EventHandler(){}

    /*
        EVENTS
    - LoadEvents() -> DONE
    - PrintEvents() -> DONE
    - SearchEvents() -> DONE
    - EventBuilder() -> DONE
    - RegisterEvents() -> DONE
    - ReadSingleEvent() -> DONE
    - UpdateEvents() -> DONE
    - DeleteEvents() -> DONE
    */
    
    public static void Event_Inator (Scanner miao, LocalDate d){
        boolean running = true;
        while (running){
            System.out.println(
                "\nEvents for " + d.getMonth() + " " + d.getYear() + ":\n" +
                "  1. Load this month's events\n" +
                "  2. Search events\n" +
                "  3. Create event\n" +
                "  4. Read event\n" +
                "  5. Update event\n" +
                "  6. Delete event\n" +
                "  0. Back to calendar"
            );
            System.out.print("Choose an action: ");
            String input = miao.nextLine();

            switch (input){
                case "1" -> {
                    ArrayList<Event> gioie = LE(d.withDayOfMonth(1)); // it should load the incoming events for the month currently seeing
                    PR(gioie);
                }
                case "2" -> PR(SE(d, miao));
                case "3" -> System.out.println(RE(miao, d) ? "Event created." : "Event could not be created.");
                case "4" -> RSE(miao, d);
                case "5" -> System.out.println(UE(miao, d) ? "Event updated." : "Event could not be updated.");
                case "6" -> System.out.println(DE(miao, d) ? "Event deleted." : "Event could not be deleted.");
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // UE -> Update Event
    private static boolean UE(Scanner miao, LocalDate d){
        try {
            Event old = SE(d, miao).get(0);
            Event e = MBE(miao, d);
            DbHandler.Update(ABE(old.GetId(), e.event(), e.GetObj(), e.GetWhen(), e.GetDone(), old.GetCreated()));
            return true;
        } catch (Exception a){
            a.printStackTrace();
            return false;
        }
    }

    // DE -> Delete Event
    private static boolean DE(Scanner miao, LocalDate d){
        Event e = SE(d, miao).get(0);
        int td = e.GetId();
        try{
            DbHandler.Kill(td);
            return true;
        } catch (Exception a){
            a.printStackTrace();
            return false;
        }
    }

    // RSE -> Read Single Event
    private static void RSE(Scanner miao, LocalDate d){
        PSR(DbHandler.Find(MBE(miao, d)).get(0));
    }

    // RE -> register event
    private static boolean RE(Scanner miao, LocalDate d){
        try {
            Event e = MBE(miao, d);
            DbHandler.Create(e);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ABE .> Automatic Build Event
    private static Event ABE (Integer id, Integer event, String obj, LocalDate d, Boolean done, LocalDate created){
        return new Event(id, event, obj, d, done, created);
    }

    // MBE -> Build Event
    private static Event MBE(Scanner miao, LocalDate d){
        int event = 0;
        while (event < 1 || event > 3){
            System.out.print("event table: 1 = bday 2 = appointment 3 = reminder: ");
            String value = miao.nextLine();
            try {
                event = Integer.parseInt(value);
            } catch (NumberFormatException e){
                event = 0;
            }
            if (event < 1 || event > 3){
                System.out.println("invalid value.");
            }
        }
        System.out.print("object: ");
        String obj = miao.nextLine();
        System.out.println("\n");
        Event e = new Event(null,event, obj, d, false, LocalDate.now());
        return e;
    }

    // SE -> Search Events
    private static ArrayList<Event> SE(LocalDate d, Scanner miao){
        ArrayList<Event> gioie = new ArrayList<>();
        Event e = MBE(miao, d);
        gioie.addAll(DbHandler.Find(e));
        if (gioie.isEmpty()){
            return null;
        }
        return gioie;
    }

    // LE -> Load Events (of the month being seen)
    private static ArrayList<Event> LE(LocalDate d){
        ArrayList<Event> results = new ArrayList<>();
        for (int i = d.getDayOfMonth(); i < d.lengthOfMonth() +1; i++){
            LocalDate temp = LocalDate.of(d.getYear(),d.getMonthValue(),i);
            Event e = new Event(null, null, null, temp, null, null);
            results.addAll(DbHandler.Find(e));
        }
        return results;
    }

    // PR -> print results
    private static void PR(ArrayList<Event> e){
        System.out.println("Events:");
        for (Event x : e){
            String tipo = converti(x.event());
            System.out.println(tipo + " - " + x.GetObj() + " - " + x.GetWhen() + " - " + x.GetDone());
        }
    }

    // converts type
    private static String converti(int event){
        return DbHandler.Tipo(event);
    }

    // PSR -> print single result
    private static void PSR(Event e){
    System.out.println(
        DbHandler.Tipo(e.event()) + " - " +
        e.GetObj() + " - " +
        e.GetWhen() + " - " +
        e.GetDone()
        );
    }
}

// last line