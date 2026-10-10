// first line

package app.core;

import app.helpers.events.InputReader;
public class Core {

    private Core(){
        // to prevent instantiation
    }

    // GETS THE COMMAND NUMBER
    public static int start(){
        menu();
        
        while (true){
            System.out.println("\n\nrequest: ");
            String temp_cmd = InputReader.WhatTs().trim();
            try {
                int quackie = Integer.parseInt(temp_cmd);
                if (quackie == -1 || CM.contains(quackie)){
                    return quackie;
                }
            }
            catch (NumberFormatException ignored){
                // invalid number
            }
            System.out.println("\ninvalid input, pls retry.\n");
        }
    }

    // MENÙ
    static void menu (){
        CM.printMenu();
    }
}

// last line