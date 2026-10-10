// first line

package app.commands.internet;
import app.commands.Command;
import app.helpers.events.InputReader;
import app.modules.internet.WebSearch;
import java.util.Map;
import java.util.Hashtable;

public class WebSearchCmd implements Command {

    @Override
    public void execute(){

        Map<Integer, String> options = new Hashtable<>();
        options.put(1, "web_search");
        options.put(2, "youtube");
        options.put(3, "wiki");
        for (int i = 0; i < options.size(); i++){
            System.out.println((i+1) +". " + options.get(i+1));
        }
        System.out.print("\nchoice: ");
        String choice;
        int loop = -1;
        while(loop < 1 || loop > 3){
            choice = InputReader.WhatTs();
            if (choice.isBlank()){
                return;
            }
            loop = Integer.parseInt(choice);
            if (loop <1 || loop > 3){
                System.out.println("\ncare to try again?\n" + loop + "isn't in the given range");
            }
        }
        System.out.println("\n");
        WebSearch.DispatchSearch(options.get(loop).toString());
    }
}

// last line