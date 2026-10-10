// first line

//CM = CommandManager

package app.core;
import app.commands.Command;
import app.commands.ai.*;
import app.commands.internet.*;
import app.helpers.events.InputReader;
import app.commands.computer.*;
import app.commands.general.TimeDateCmd;
import app.commands.general.WeatherCmd;

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

        // INTERNET
        register("internet", "search the web", new WebSearchCmd()); // working on this currentl

        // GENERAL
        register("general", "time and date", new TimeDateCmd());
        register("general", "weather anywhere", new WeatherCmd());
        register("general", "calendar - TODO list - reminders", new CalendarCmd());
        // we got this far =)
        register("general", "send emails", new SendEmailCmd());

        // AI
        register("AI", "vocal commands", new VoiceCommandCmd());
        register("AI", "vocal answers", new VoiceAnswerCmd());
        register("AI", "OCR", new OcrCmd());

        // CHESS PLAYER (machine learning?)
        // register("games", "chess", new ChessCmd());
        // register("games", "tick-tack-toe", new TTTCmd());
        // register("games", "campo fiorito", new CFCmd());
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

    public static void execute(int number){

        if (!contains(number)){
            System.out.println("\nunknown cmd sry ==^.^==");
            return;
        }
        commands.get(number - 1).command().execute();
    }

    public record CommandItem(int number, String category, String label, Command command) {
    }

}
// last line