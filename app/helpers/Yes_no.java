// first line

package app.helpers;

public class Yes_no {
    private Yes_no(){}

    public static boolean yes_no(String z){

        if((z == "yes") ||
            (z == "yup") ||
            (z == "y") ||
            (z == "1") ||
            (z == "sure") ||
            (z == "yep") ||
            (z == "sì") ||
            (z == "si") ||
            (z.contains("yes")) ||
            (z.contains("yup"))||
            (z.contains("sì")) ||
            (z.contains("si"))){
            return true;
        }

        return false;
    }
}

// last line