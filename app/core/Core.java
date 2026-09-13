// first line

package app.core;
import java.util.Scanner;

public class Core {

    private static final int MIN_CMD = 1;
    private static final int MAX_CMD = 12;

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
                if ((quackie >= MIN_CMD && quackie <= MAX_CMD) || (quackie == -1)){
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
        System.out.println(

"""
here's the menù:
        \n
        \n  |==============|                |==============|
        \n  | computer lvl |                |  general lvl |
        \n  |==============|                |==============|
        \n  1. search files              7. send emails
        \n  2. open files                    8. time and date
        \n  3. delete files                  9. wather anywhere
        \n  4. edit files                    10. search the web
        \n  5. open apps                     11. answer questions (wiki)
        \n  6. play music                    12. calendar - TODO list - reminders
        \n
        \n  |================|
        \n  |coming soon lvl |
        \n  |================|
        \n  vocal commands
        \n  vocal answers
        \n  OCR
        \n  basic talking(?)\n
    """);
    }
}

// last line