// first line

package app.commands.ai;
import app.modules.ai.Chatbot;
import app.commands.Command;
import java.util.Scanner;

public class ChatbotCmd implements Command {

    @Override
    public void execute(Scanner miao){
        Chatbot.chat(miao);
    }
}

// last line