// first line

package app.commands.ai;
import app.modules.ai.VoiceAnswer;
import app.commands.Command;
import java.util.Scanner;

public class VoiceAnswerCmd implements Command {

    @Override
    public void execute(Scanner miao){
        VoiceAnswer.answer(miao);
    }
}

// last line