// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.TimeDate;
import java.util.Scanner;
import app.helpers.Yes_no;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public class TimeDateCmd implements Command {

    @Override
    public void execute(Scanner miao){
        System.out.print("\nlocal?\nchoce: ");
        String choice = miao.nextLine().toLowerCase();
        ZonedDateTime zona = ZonedDateTime.now(ZoneId.systemDefault());
        String city;
        if (Yes_no.yes_no(choice)){
            city = zona.getZone().toString();
            String time = zona.toLocalTime().toString();
            String date = zona.toLocalDate().toString();
            System.out.println("Fuso orario: " + city + " - time: " + time + " - date: " + date);
        } else{
            System.out.print("\ntarget city name: ");
            city = miao.nextLine().toLowerCase();
            TimeDate.timedate(miao, city);
        }
    }
}

// last line