// first line

package app.core;

public class Dispatcher {

    private Dispatcher(){
        // still for that instantiantion or whatever the duck that was
    }

    public static void dispatch(int cmd){
        CM.execute(cmd);
    }
}

// last line