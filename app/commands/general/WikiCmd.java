// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.Wiki;
import java.util.Scanner;

public class WikiCmd implements Command {

    @Override
    public void execute(Scanner miao){
        Wiki.search(miao);
    }
}

// last line