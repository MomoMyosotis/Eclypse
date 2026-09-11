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
    public static int start(){
        Scanner miao = new Scanner(System.in);
        menu();
        
        while (true){
            System.out.println("\n\nrequest: ");
            String temp_cmd = miao.nextLine().trim();
            try {
                int quackie = Integer.parseInt(temp_cmd);
                if (quackie >= MIN_CMD && quackie <= MAX_CMD){
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
        \n
        \n==============
        \ncomputer lvl
        \n==============
        \n1. search for files
        \n2. open files
        \n3. delete files
        \n4. edit files
        \n5. open apps
        \n6. play music
        \n
        \n
        \n==============
        \ngeneral lvl
        \n==============
        \n7. send emails
        \n8. time and date
        \n9. wather anywhere
        \n10. search the web
        \n11. answer questions (wiki)
        \n12.calendar - TODO list - reminders
        \n
        \n
        \n==============
        \ncoming soon lvl:
        \n==============
        \nvocal commands
        \nvocal answers
        \nOCR
        \nbasic talking(?)
    """);
    }
}

// last line