// first line

package app.commands.internet;
import app.commands.Command;
import app.helpers.events.InputReader;
import app.modules.internet.WebSearch;
import java.util.Map;
import java.util.Hashtable;
import java.util.Scanner;

public class WebSearchCmd implements Command {

    @Override
    public void execute(Scanner miao){

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
            choice = InputReader.WhatTs(miao);
            if (choice.isBlank()){
                return;
            }
            loop = Integer.parseInt(choice);
            if (loop <1 || loop > 3){
                System.out.println("\ncare to try again?\n" + loop + "isn't in the given range");
            }
        }
        System.out.println("\n");
        WebSearch.DispatchSearch(miao, options.get(loop).toString());
    }
}

// last line