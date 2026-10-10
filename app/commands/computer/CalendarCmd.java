// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.Calendar;
import java.time.LocalDate;

public class CalendarCmd implements Command {

    @Override
    public void execute(){
        LocalDate current = LocalDate.now();
        Calendar.organise(current);
        
        
    }
}

// last line