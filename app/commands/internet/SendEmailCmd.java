// first line

package app.commands.internet;
import app.commands.Command;
import app.modules.internet.MailHandler;

public class SendEmailCmd implements Command {

    @Override
    public void execute(){
        MailHandler.handle();
    }
}

// last line