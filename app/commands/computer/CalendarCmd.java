// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.Calendar;
import java.util.Scanner;
import java.time.LocalDate;

public class CalendarCmd implements Command {

    @Override
    public void execute(Scanner miao){
        LocalDate current = LocalDate.now();
        Calendar.organise(miao, current);
        
        
    }
}

// last line