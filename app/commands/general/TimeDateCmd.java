// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.TimeDate;
import java.util.Scanner;

public class TimeDateCmd implements Command {

    @Override
    public void execute(Scanner miao){
        TimeDate.timedate(miao);
    }
}

// last line