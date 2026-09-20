// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.Calendar;
import java.util.Scanner;

public class CalendarCmd implements Command {

    @Override
    public void execute(Scanner miao){
        Calendar.organise(miao);
    }
}

// last line