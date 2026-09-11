// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.Wiki;

public class WikiCmd implements Command {

    @Override
    public void execute(){
        Wiki.search();
    }
}

// last line