// first line

//CM = CommandManager

package app.core;
import app.commands.Command;
import app.commands.ai.*;
import app.commands.general.*;
import app.commands.computer.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        // CHESS PLAYER
    }


    public static void execute(int quack, Scanner miao){

        Command gioia = commands.get(quack);
        if (gioia == null){
            System.out.println("\nunknown cmd sry ==^.^==");
            return;
        }
        gioia.execute(miao);
    }
}

// last line