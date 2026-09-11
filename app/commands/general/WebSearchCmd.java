// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.WebSearch;

public class WebSearchCmd implements Command {

    @Override
    public void execute(){
        WebSearch.search();
    }
}

// last line