// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.TimeDate;

public class TimeDateCmd implements Command {

    @Override
    public void execute(){
        TimeDate.timedate();
    }
}

// last line