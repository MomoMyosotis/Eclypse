// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.SendEmail;

public class SendEmailCmd implements Command {

    @Override
    public void execute(){
        SendEmail.send();
    }
}

// last line