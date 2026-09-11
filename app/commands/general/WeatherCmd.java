// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.Weather;

public class WeatherCmd implements Command {

    @Override
    public void execute(){
        Weather.weather();
    }
}

// last line