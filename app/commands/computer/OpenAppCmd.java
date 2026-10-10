// first line

package app.commands.computer;
import app.commands.Command;
import app.helpers.events.InputReader;
import app.helpers.local.AppList;
import app.platform.App;
import app.modules.computer.AppOpen;

public class OpenAppCmd implements Command {

    @Override
    public void execute(){
        AppList.lista();
        System.out.println("\nnumber app to start: ");
        int zighy = InputReader.WhatTsInt();
        try {
            App app = AppList.getApp(zighy);
            AppOpen.openApp(app);
        } catch (Exception e){
            System.out.println("OpenFIle failed.\nerror: " +e);
        }
    }
}

// last line