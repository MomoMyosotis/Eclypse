// first line

package app.helpers;
import java.util.Scanner;
import app.modules.computer.FileSearch;

public class Filepath {
    
    public static String FP (Scanner miao) {

        System.out.println("\nDo you know the full path?\nanswer: ");
        String knows = miao.nextLine().trim();
        String zighy;
        if (Yes_no.yes_no(knows)){
            System.out.println("\nfiles full path (with name and format): ");
            zighy = miao.nextLine().trim();
        } else{
            System.out.println("\nwe can search for it.\nname: ");
            zighy = miao.nextLine();
            try{
                FileSearch.search(zighy, 3);
            } catch (Exception e){
                System.out.println("Filepath error : " + e);
            }
            System.out.println("\nplease type (or paste) here the full path:\n");
            zighy = miao.nextLine().trim();
        }
        return zighy;
    }
}

// last line