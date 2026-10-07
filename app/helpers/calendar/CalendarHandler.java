// first line

package app.helpers.calendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class CalendarHandler {
    private CalendarHandler(){}

    /*
        =============
            CALENDAR
        - ShowCalendar() -> done
        - BrowseCalendar() -> done
        - GOTOdate() -> done
    */
    private static final String WEEK[] = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    private static final int Y = 6;
    private static final int X = 7;

    public static void CreateMonth(LocalDate d){
        int days = d.lengthOfMonth(); // days in a month?

        LocalDate primo = d.withDayOfMonth(1);
        DayOfWeek pday = primo.getDayOfWeek(); // which day of the week the month stharts with?
        int n = pday.getValue();

        int[][] month = new int[Y][X];

        month = PM(days, month, n, primo);
        PD(d);
        PWD();
        PMT(month);
    }

    // PD -> Print Datew
    private static void PD(LocalDate d){
        String quack = d.getYear() + " - " + d.getMonth();
        int larghezza = X *10;
        int spaces = (larghezza - quack.length()) /2;
        System.out.printf("".repeat(spaces) + quack);
        System.out.println("");
    }

    // PT -> populate month's table
    private static int[][] PM(int days, int[][] month, int pday, LocalDate primo){
        // days -> giorni del mese
        // pday -> numero giorno settimana del mese
        LocalDate start = primo.with(
            TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY) // gets the first omnday of the matrix's date
        );
        for (int i = 0; i < 42; i++) {
            LocalDate date = start.plusDays(i);
            int riga = i / X;
            int colonna = i % X;
            month[riga][colonna] = date.getDayOfMonth();
        }
        return month;
    }

    // PWD -> print week's day's names
    private static void PWD(){
        for (String x : WEEK){
            System.out.printf("%-10s", x);
        }
        System.out.println();
    }

    //PMT -> print month table
    private static void PMT(int[][] month){
        for (int i = 0; i < Y; i++){
            for (int j = 0; j < X; j++){
                System.out.printf("%-10d", month[i][j]);
            }
            System.out.println();
        }
    }
}
// last line