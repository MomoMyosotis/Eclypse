// first line

package app.commands.ai;
import app.modules.ai.VoiceCommand;
import app.commands.Command;

public class VoiceCommandCmd implements Command {

    @Override
    public void execute(){
        VoiceCommand.cmd();
    }
}

// last line