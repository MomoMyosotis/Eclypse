// first line

package app.helpers;

public class Yes_no {
    private Yes_no(){}

    public static boolean yes_no(String z){

        if(z.equals("") ||
            (z.equals("yes")) ||
            (z.equals("yup")) ||
            (z.equals("y")) ||
            (z.equals("1")) ||
            (z.equals("sure")) ||
            (z.equals("yep")) ||
            (z.equals("sì")) ||
            (z.equals("si")) ||
            (z.equals("k")) ||
            (z.contains("ok")) ||
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