// first

package app;
import app.core.Core;
import app.core.Dispatcher;
import app.helpers.Clear;

public class Main {
    public static void main (String[] args){

        System.out.println("Eclypse is being loaded...");

        int command = Core.start();
        Clear.clean();
        Dispatcher.dispatch(command);

        System.out.println("\nEclypse closing.");
    }
}

// last line