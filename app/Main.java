// first

package app;
import java.util.Scanner;

import app.core.Core;
import app.core.Dispatcher;
import app.helpers.Clear;

public class Main {
    public static void main (String[] args){

        System.out.println("Eclypse is being loaded...");
        
        Scanner miao = new Scanner(System.in);
        while (true){
            int command = Core.start(miao);
            Clear.clean();
            if (command == -1){
                break;
            }
            Dispatcher.dispatch(command, miao);
            System.out.println("\npress enter to continue:\n");
            String enter = miao.nextLine();
        }
        System.out.println("\nEclypse closing.");
    }
}

// last line