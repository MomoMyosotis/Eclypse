// first line

package app.commands.ai;
import app.modules.ai.VoiceCommand;
import app.commands.Command;
import java.util.Scanner;

public class VoiceCommandCmd implements Command {

    @Override
    public void execute(Scanner miao){
        VoiceCommand.cmd(miao);
    }
}

// last line