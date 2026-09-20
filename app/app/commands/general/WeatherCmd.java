// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.Weather;
import java.util.Scanner;
import app.helpers.DuckingWhere;

public class WeatherCmd implements Command {

    @Override
    public void execute(Scanner miao){

        System.out.print("\nCity name: ");
        String city = miao.nextLine().toLowerCase().replace(" ", "%20");
        System.out.println("\n");
        double[] coords = DuckingWhere.geocoder(city);
        Weather.weather(coords);
    }
}

// last line