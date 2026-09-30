// first line

package app.helpers.local;
import java.util.Scanner;

import app.helpers.events.InputReader;
import app.modules.computer.FileSearch;
public class Filepath {
    
    public static String FP (Scanner miao) {

        System.out.println("\nDo you know the full path?\nanswer: ");
        String knows = InputReader.WhatTs(miao).trim();
        String zighy;
        if (InputReader.yes_no(knows) == true){
            System.out.println("\nfiles full path (with name and format): ");
            zighy = InputReader.WhatTs(miao).trim();
        } else{
            System.out.println("\nwe can search for it.\nname: ");
            zighy = InputReader.WhatTs(miao);
            try{
                FileSearch.search(zighy, 3);
            } catch (Exception e){
                System.out.println("Filepath error : " + e);
            }
            System.out.println("\nplease type (or paste) here the full path:\n");
            zighy = InputReader.WhatTs(miao).trim();
        }
        return zighy;
    }
}

// last line