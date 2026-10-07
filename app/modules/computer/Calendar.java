// first line

package app.modules.computer;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;
import app.helpers.calendar.CalendarHandler;
import app.helpers.calendar.EventHandler;
import app.helpers.general.Clear;

public class Calendar {
    private Calendar(){}

    public static void organise(Scanner miao, LocalDate date){
        boolean running = true;
        while (running){
            Clear.clean();
            CalendarHandler.CreateMonth(date);
            System.out.println("\nSelected date: " + date);
            System.out.println(
                "Calendar and events:\n" +
                "  1. Previous month       2. Next month\n" +
                "  3. Previous year        4. Next year\n" +
                "  5. Go to date           6. Open event panel\n" +
                "  0. Quit"
            );
            System.out.print("Choose an action: ");
            String input = miao.nextLine();

            switch (input){
                case "1" -> date = date.minusMonths(1);
                case "2" -> date = date.plusMonths(1);
                case "3" -> date = date.minusYears(1);
                case "4" -> date = date.plusYears(1);
                case "5" -> date = readDate(miao, date);
                case "6" -> EventHandler.Event_Inator(miao, date);
                case "0" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static LocalDate readDate(Scanner miao, LocalDate currentDate){
        try {
            System.out.print("Year (blank keeps " + currentDate.getYear() + "): ");
            String value = miao.nextLine();
            int year = value.isBlank() ? currentDate.getYear() : Integer.parseInt(value);

            System.out.print("Month (blank keeps " + currentDate.getMonthValue() + "): ");
            value = miao.nextLine();
            int month = value.isBlank() ? currentDate.getMonthValue() : Integer.parseInt(value);

            System.out.print("Day (blank keeps " + currentDate.getDayOfMonth() + "): ");
            value = miao.nextLine();
            int day = value.isBlank() ? currentDate.getDayOfMonth() : Integer.parseInt(value);

            return LocalDate.of(year, month, day);
        } catch (NumberFormatException | DateTimeException e){
            System.out.println("Invalid date. Keeping " + currentDate + ".");
            return currentDate;
        }
    }
}

// last line