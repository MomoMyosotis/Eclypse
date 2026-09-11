// first line

package app.commands.computer;
import app.modules.computer.AppOpen;
import app.commands.Command;

public class OpenAppCmd implements Command {

    @Override
    public void execute(){
        AppOpen.open();
    }
}

// last line