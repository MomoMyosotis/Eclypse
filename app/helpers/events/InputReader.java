// first line

package app.helpers.events;
import java.util.Scanner;

public class InputReader {
    private InputReader(){}

    public static String WhatTs(Scanner miao){
        String ts = miao.nextLine();
        return ts;
    }

    public static int WhatTsInt(Scanner miao){
        int i = miao.nextInt();
        return i;
    }
    
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