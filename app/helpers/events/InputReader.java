// first line

package app.helpers.events;
import java.util.Scanner;

public class InputReader {
    private InputReader(){}
    private static final Scanner MIAO = new Scanner(System.in);

    public static void meowCloser(){
        MIAO.close();
    }

    public static String WhatTs(){
        return MIAO.nextLine();
    }

    public static int WhatTsInt(){
        return MIAO.nextInt();
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