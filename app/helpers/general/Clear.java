// first line

package app.helpers.general;

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