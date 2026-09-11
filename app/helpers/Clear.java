// first line

package app.helpers;

public class Clear {

    private Clear(){
        // to prevent blablaination
    }

    public static void clean(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
// last line