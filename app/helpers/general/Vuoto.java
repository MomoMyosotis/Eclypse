package app.helpers.general;

public class Vuoto {
    private Vuoto(){}

    public static boolean v(String boop){
        return boop == null || boop.isBlank();
    }
}
