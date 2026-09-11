// first line

package app.commands.ai;
import app.modules.ai.VoiceAnswer;
import app.commands.Command;

public class VoiceAnswerCmd implements Command {

    @Override
    public void execute(){
        VoiceAnswer.answer();
    }
}

// last line