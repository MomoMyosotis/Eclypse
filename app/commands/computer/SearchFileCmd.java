// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.FileSearch;
import java.util.Scanner;

public class SearchFileCmd implements Command {

    @Override
    public void execute(){
        Scanner quack = new Scanner(System.in);
        System.out.println("\nfile name: ");
        String zighy = quack.nextLine().trim();
        System.out.println("\nmax results: ");
        int mr = Integer.parseInt(quack.nextLine().trim());
        try{
            if (mr <= 0){
                System.out.println("\nmax results must be > 0.\n");
                quack.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("\ninvalid number of results.\n");
            quack.close();
            return;
        }
        quack.close();
        FileSearch.search(zighy, mr);
    }
}

// last line