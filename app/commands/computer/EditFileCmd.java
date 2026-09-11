// first line

package app.commands.computer;
import app.modules.computer.FileEdit;
import app.commands.Command;

public class EditFileCmd implements Command {

    @Override
    public void execute(){
        FileEdit.edit();
    }
}

// last line