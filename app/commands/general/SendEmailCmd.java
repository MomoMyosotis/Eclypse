// first line

package app.commands.general;
import app.commands.Command;
import app.modules.general.SendEmail;
import java.util.Scanner;

public class SendEmailCmd implements Command {

    @Override
    public void execute(Scanner miao){
        SendEmail.send(miao);
    }
}

// last line