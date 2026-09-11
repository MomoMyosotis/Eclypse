// first line

package app.commands.ai;
import app.modules.ai.Chatbot;
import app.commands.Command;

public class ChatbotCmd implements Command {

    @Override
    public void execute(){
        Chatbot.chat();
    }
}

// last line