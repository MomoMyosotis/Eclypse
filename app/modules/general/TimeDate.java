// frist line

package app.modules.general;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
import app.helpers.DuckingWhere;

public class TimeDate {
    private TimeDate(){}

    public static void timedate(Scanner miao, String city){
        String duck = DuckingWhere.duckingwhere(city);
        ZonedDateTime zona = ZonedDateTime.now(ZoneId.of(duck));
        String città = zona.getZone().toString();
        String time = zona.toLocalTime().toString();
        String date = zona.toLocalDate().toString();
        System.out.println("Fuso orario: " + città + " - time: " + time + " - date: " + date);
    }
}

// last line