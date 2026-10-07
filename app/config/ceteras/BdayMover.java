// first line

package app.config.ceteras;
import java.time.LocalDate;
import app.config.db.DbHandler;
import app.helpers.calendar.Event;
import app.helpers.calendar.EventHandler;

public class BdayMover {
    private BdayMover(){}

    public static boolean BdayInator(){
        try {
            SAE();
            System.out.println("\nbdays are in order!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // SAE -> search all results (just for BES())
    private static void SAE (){
        Event evee = EventHandler.ABE(null, 1, null, null, null, null);
        for (Event e : DbHandler.Find(evee)){
            BES(e);
        }
    }

    // BES -> Birthday Events Skipper
    private static void BES(Event e){
        if (e.GetWhen() != null && e.GetWhen().isBefore(LocalDate.now())){
            int yay = LocalDate.now().plusYears(1).getYear();
            LocalDate bl = LocalDate.of(yay, e.GetWhen().getMonthValue(), e.GetWhen().getDayOfMonth());
            DbHandler.Update(EventHandler.ABE(e.GetId(), e.event(), e.GetObj(),bl, e.GetDone(), e.GetCreated()));
        }
    }
}

// last line