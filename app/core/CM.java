// first line

//CM = CommandManager

package app.core;
import app.commands.Command;
import app.commands.ai.*;
import app.commands.general.*;
import app.commands.computer.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CM {

    private CM (){}

    private static final List<CommandItem> commands = new ArrayList<>();

    static {

        // COMPUTER
        register("computer", "search files", new SearchFileCmd());
        register("computer", "open files", new OpenFileCmd());
        register("computer", "delete files", new DeleteFileCmd());
        register("computer", "edit files", new EditFileCmd());
        register("computer", "open apps", new OpenAppCmd());
        register("computer", "image/file converters", new ConverterCmd());

        // GENERAL
        register("general", "send emails", new SendEmailCmd());
        register("general", "time and date", new TimeDateCmd());
        register("general", "weather anywhere", new WeatherCmd());
        register("general", "search the web", new WebSearchCmd());
        register("general", "answer questions (wiki)", new WikiCmd());
        register("general", "calendar - TODO list - reminders", new CalendarCmd());
        register("general", "play music", new PlayMusicCmd());

        // AI / COMING SOON
        register("coming soon", "vocal commands", new VoiceCommandCmd());
        register("coming soon", "vocal answers", new VoiceAnswerCmd());
        register("coming soon", "OCR", new OcrCmd());
        register("coming soon", "basic talking(?)", new ChatbotCmd());

        // CHESS PLAYER (machine learning?)
    }

    private static void register(String category, String label, Command command){
        commands.add(new CommandItem(commands.size() + 1, category, label, command));
    }

    public static List<CommandItem> all(){
        return Collections.unmodifiableList(commands);
    }

    public static boolean contains(int number){
        return number >= 1 && number <= commands.size();
    }

    public static void printMenu(){
        String currentCategory = "";
        System.out.println("\nhere's the menu:\n");
        for (CommandItem item : commands){
            if (!item.category().equals(currentCategory)){
                currentCategory = item.category();
                System.out.println("\n[" + currentCategory + "]");
            }
            System.out.printf("  %d. %s%n", item.number(), item.label());
        }
    }

    public static void execute(int number, Scanner miao){

        if (!contains(number)){
            System.out.println("\nunknown cmd sry ==^.^==");
            return;
        }
        commands.get(number - 1).command().execute(miao);
    }

    public record CommandItem(int number, String category, String label, Command command) {
    }
}

// last line