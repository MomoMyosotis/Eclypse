// first line

package app.commands.computer;
import app.commands.Command;
import app.helpers.events.InputReader;
import app.modules.computer.FileSearch;

public class SearchFileCmd implements Command {

    @Override
    public void execute(){
        System.out.println("\nfile name: ");
        
        String zighy = InputReader.WhatTs().trim();
        System.out.println("\nmax results: ");
        int mr = Integer.parseInt(InputReader.WhatTs().trim());
        try{
            if (mr <= 0){
                System.out.println("\nmax results must be > 0.\n");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\ninvalid number of results.\n");
            return;
        }
        FileSearch.search(zighy, mr);
    }
}

// last line