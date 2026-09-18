// first

package app;
import java.util.Scanner;
import app.core.Core;
import app.core.Dispatcher;
import app.helpers.Clear;
import app.helpers.Config;

public class Main {
    public static void main (String[] args){

        System.out.println("Eclypse is being loaded...");
        
        if (!Config.Check()){
            System.out.println("some of the dependencies required cannot be configurated.\nERR 01");
            return;
        }

        Scanner miao = new Scanner(System.in);
        while (true){
            int command = Core.start(miao);
            Clear.clean();
            if (command == -1){
                break;
            }
            Dispatcher.dispatch(command, miao);
            System.out.print("\npress enter to continue:\n");
            miao.nextLine();
            System.out.println("");
        }
    }
}

// last line