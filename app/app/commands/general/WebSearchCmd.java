// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.WebSearch;
import java.util.Scanner;

public class WebSearchCmd implements Command {

    @Override
    public void execute(Scanner miao){
        WebSearch.search(miao);
    }
}

// last line