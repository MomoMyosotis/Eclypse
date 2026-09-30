// first line

package app.core;
import java.util.Scanner;

public class Dispatcher {

    private Dispatcher(){
        // still for that instantiantion or whatever the duck that was
    }

    public static void dispatch(int cmd, Scanner miao){
        CM.execute(cmd, miao);
    }
}

// last line