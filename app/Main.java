// first

package app;
import app.config.StatusChecker;
import app.core.Core;
import app.core.Dispatcher;
import app.helpers.general.Clear;
import app.helpers.events.InputReader;

public class Main {
    public static void main (String[] args){

        System.out.println("Eclypse is being loaded...");
        if (!StatusChecker.checker()){
            System.out.println("some of the dependencies required cannot be configurated.\nERR 01");
            InputReader.meowCloser();
            return;
        }
        while (true){
            int command = Core.start();
            Clear.clean();
            if (command == -1){
                break;
            }
            Dispatcher.dispatch(command);
            System.out.print("\npress enter to continue:\n");
            InputReader.WhatTs();
            System.out.println("");
        }
        InputReader.meowCloser();
    }
}

// last line