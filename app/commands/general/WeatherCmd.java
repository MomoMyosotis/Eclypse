// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.Weather;
import java.util.Scanner;

public class WeatherCmd implements Command {

    @Override
    public void execute(Scanner miao){
        Weather.weather(miao);
    }
}

// last line