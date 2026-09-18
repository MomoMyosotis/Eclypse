// first line

package app.core;
import java.util.Scanner;

public class Core {

    private Core(){
        // to prevent instantiation
    }

    // GETS THE COMMAND NUMBER
    public static int start(Scanner miao){
        menu();
        
        while (true){
            System.out.println("\n\nrequest: ");
            String temp_cmd = miao.nextLine().trim();
            try {
                int quackie = Integer.parseInt(temp_cmd);
                if (quackie == -1 || CM.contains(quackie)){
                    return quackie;
                }
            }
            catch (NumberFormatException ignored){
                // invalid number
            }
            System.out.println("\ninvalid input, pls retry.\n");
        }
    }

    // MENÙ
    static void menu (){
        CM.printMenu();
    }
}

// last line