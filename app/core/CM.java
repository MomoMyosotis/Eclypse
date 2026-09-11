// first line

//CM = CommandManager

package app.core;
import app.commands.Command;
import app.commands.ai.*;
import app.commands.general.*;
import app.commands.computer.*;
/*/
import app.commands.computer.SearchFileCmd;
import app.commands.computer.OpenFileCmd;
import app.commands.computer.DeleteFileCmd;
import app.commands.computer.EditFileCmd;
import app.commands.computer.OpenAppCmd;
import app.commands.general.layMusicCmd;
import app.commands.general.SendEmailCmd;
import app.commands.general.TimeDateCmd;
import app.commands.general.WeatherCmd;
import app.commands.general.WebSearchCmd;
import app.commands.general.WikiCmd;
import app.commands.general.CalendarCmd;
import app.commands.ai.VoiceCommandCmd;
import app.commands.ai.VoiceAnswerCmd;
import app.commands.ai.OcrCmd;
import app.commands.ai.ChatbotCmd;
*/
import java.util.HashMap;
import java.util.Map;

public class CM {

    private CM (){}

    private static final Map<Integer, Command> commands = new HashMap<>();

    static {

        // COMPUTER
        commands.put(1, new SearchFileCmd());
        commands.put(2, new OpenFileCmd());
        commands.put(3, new DeleteFileCmd());
        commands.put(4, new EditFileCmd());
        commands.put(5, new OpenAppCmd());
        commands.put(6, new PlayMusicCmd());

        // GENERAL
        commands.put(7, new SendEmailCmd());
        commands.put(8, new TimeDateCmd());
        commands.put(9, new WeatherCmd());
        commands.put(10, new WebSearchCmd());
        commands.put(11, new WikiCmd());
        commands.put(12, new CalendarCmd());

        // AI / COMING SOON
        commands.put(13, new VoiceCommandCmd());
        commands.put(14, new VoiceAnswerCmd());
        commands.put(15, new OcrCmd());
        commands.put(16, new ChatbotCmd());
    }


    public static void execute(int quack){

        Command gioia = commands.get(quack);
        if (gioia == null){
            System.out.println("\nunknown cmd sry ==^.^==");
            return;
        }
        gioia.execute();
    }
}

// last line