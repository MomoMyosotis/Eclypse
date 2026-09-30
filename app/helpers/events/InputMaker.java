// frist line

package app.helpers.events;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class InputMaker {
    private InputMaker(){}

    // preme K (play su youtube)
    public static void pressPlay(int delay) {
        try {
            Robot r = new Robot();
            r.delay(delay);                 // aspetta che la pagina carichi (10 secondi)
            r.keyPress(KeyEvent.VK_K);
            r.keyRelease(KeyEvent.VK_K);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// last line