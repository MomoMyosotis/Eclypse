// first line

package app.commands.computer;
import app.commands.Command;
import app.modules.computer.FileSearch;
import java.util.Scanner;

public class SearchFileCmd implements Command {

    @Override
    public void execute(Scanner miao){
        System.out.println("\nfile name: ");
        String zighy = miao.nextLine().trim();
        System.out.println("\nmax results: ");
        int mr = Integer.parseInt(miao.nextLine().trim());
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