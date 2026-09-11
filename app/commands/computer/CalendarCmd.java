// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.Calendar;

public class CalendarCmd implements Command {

    @Override
    public void execute(){
        Calendar.organise();
    }
}

// last line