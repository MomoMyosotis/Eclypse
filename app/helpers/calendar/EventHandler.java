// first line

package app.helpers.calendar;
import java.time.LocalDate;
import java.util.ArrayList;
import app.config.db.DbHandler;
import app.helpers.events.InputReader;

public class EventHandler {
    private EventHandler(){}
    
    public static void Event_Inator (LocalDate d){
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
            String input = InputReader.WhatTs();

            switch (input){
                case "1" -> {
                    ArrayList<Event> gioie = LE(d.withDayOfMonth(1)); // it should load the incoming events for the month currently seeing
                    PR(gioie);
                }
                case "2" -> PR(SE(d));
                case "3" -> System.out.println(RE(d) ? "Event created." : "Event could not be created.");
                case "4" -> RSE(d);
                case "5" -> System.out.println(UE(d) ? "Event updated." : "Event could not be updated.");
                case "6" -> System.out.println(DE(d) ? "Event deleted." : "Event could not be deleted.");
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // UE -> Update Event
    private static boolean UE(LocalDate d){
        try {
            Event old = SE(d).get(0);
            Event e = MBE(d);
            DbHandler.Events.Update(ABE(old.GetId(), e.event(), e.GetObj(), e.GetWhen(), e.GetDone(), old.GetCreated()));
            return true;
        } catch (Exception a){
            a.printStackTrace();
            return false;
        }
    }

    // DE -> Delete Event
    private static boolean DE( LocalDate d){
        Event e = SE(d).get(0);
        int td = e.GetId();
        try{
            DbHandler.Events.Kill(td);
            return true;
        } catch (Exception a){
            a.printStackTrace();
            return false;
        }
    }

    // RSE -> Read Single Event
    private static void RSE( LocalDate d){
        Event e = DbHandler.Events.Find(MBE(d)).get(0);
        PSR(e);
        System.out.print("\nset done?\nchoice: ");
        if (InputReader.yes_no(InputReader.WhatTs())){
            e.SetDone(e);
        }
    }

    // RE -> register event
    private static boolean RE( LocalDate d){
        try {
            Event e = MBE(d);
            DbHandler.Events.Create(e);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ABE -> Automatic Build Event
    public static Event ABE (Integer id, Integer event, String obj, LocalDate d, Boolean done, LocalDate created){
        return new Event(id, event, obj, d, done, created);
    }

    // MBE -> Manual Build Event
    private static Event MBE( LocalDate d){
        Integer event = 0;

        while (true){
            System.out.print("event table: 1 = bday 2 = appointment 3 = reminder [ENTER] = all types (only for search (default 2)\nchoice: ");
            String value = InputReader.WhatTs();
            if (value.isEmpty()){
                event = null;
                break;
            }
            try{
                event = Integer.parseInt(value);
                if (event > 0 || event < 4){
                    break;
                }
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
            System.out.println("\ninvalid number:" + event);
        }
        System.out.print("\nfor: " + d.getDayOfWeek()+ " " + d.getDayOfMonth() + d.getMonth() + " " + d.getYear() + "?\nchoice: ");
        LocalDate quack = d;
        if (!InputReader.yes_no(InputReader.WhatTs())){
        System.out.print("\nWhen? (YYYYMMDD)\nyear: ");
        int year = InputReader.WhatTsInt();
        System.out.print("\nMonth: ");
        int month = InputReader.WhatTsInt();
        System.out.print("\nday: ");
        int day = InputReader.WhatTsInt();
        quack = LocalDate.of(year,month,day);
        }
        InputReader.WhatTs();
        System.out.print("\nobject: ");
        String obj = InputReader.WhatTs();
        System.out.println("\n");

        Event e = new Event(null,event, obj, quack, false, LocalDate.now());
        return e;
    }

    // SE -> Search Events
    private static ArrayList<Event> SE(LocalDate d){
        ArrayList<Event> gioie = new ArrayList<>();
        Event e = MBE(d);
        gioie.addAll(DbHandler.Events.Find(e));
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
            results.addAll(DbHandler.Events.Find(e));
        }
        return results;
    }

    // PR -> print results
    private static void PR(ArrayList<Event> e){
        System.out.println("Events:");
        if (e == null){
            System.out.println("\n0 results found. ==^.^==");
        } else{
            for (Event x : e){
                String tipo = converti(x.event());
                System.out.println(tipo + " - " + x.GetObj() + " - " + x.GetWhen() + " - " + x.GetDone());
            }
        }
    }

    // converts type
    private static String converti(int event){
        return DbHandler.Events.Tipo(event);
    }

    // PSR -> print single result
    private static void PSR(Event e){
    System.out.println(
        DbHandler.Events.Tipo(e.event()) + " - " +
        e.GetObj() + " - " +
        e.GetWhen() + " - " +
        e.GetDone()
        );
    }
}
// last line