// first line

package app.modules.computer;
import java.util.Scanner;
import app.helpers.calendar.CalendarHandler;
import java.time.LocalDate;

public class Calendar {
        private Calendar(){}

    public static void organise(Scanner miao, LocalDate date){

        
        CalendarHandler.CalendarInator(miao, date);

        // this file will handle the calendar module.
        
        /*
            show calendar (with search bar 4 events)
            load events()
            for now numbers, later connect to bottons or connect bottons to direct functions?
            functions list:
            =============
                CALENDAR
            - ShowCalendar() -> DONE
            - BrowseCalendar() -> DONE
            - GOTOdate() -> DONE
            =============
                EVENTS
            - LoadEvents()
            - SearchEvents() -> Find_in_interval() || speicfic date() etc 4 other Event's data
            - CreateEvents()
            - ReadEvents()
            - UpdateEvents()
            - DeleteEvents()
            ==============
                GENERAL
            - linking calendar with events
            - pop-up / notifications reminders
            - if there's an event on the calendar display it somehow
            - whenDone() -> bdays move to next year - others remove
        */
    }
}

// last line