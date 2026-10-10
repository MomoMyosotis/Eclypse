// first line

package app.modules.internet;
import java.util.ArrayList;

import app.helpers.mail.MailAccount;
import app.helpers.mail.SessionHandler;
import app.helpers.events.InputReader;

public class MailHandler {
    private MailHandler(){}

    public static void handle(){
        MailAccount bl = chooseAccount();
        if (bl == null){
            MailAccount.create();
            handle();
        } else {
            bl.authenticate();
        }
    }

    private static MailAccount chooseAccount(){
        ArrayList<MailAccount> quack = SessionHandler.init();
        System.out.println("select account");
        for (MailAccount a : quack){
            System.out.println(a.getAddress());
            if (InputReader.yes_no(InputReader.WhatTs())){
                return a;
            }
        }
        return null; // no account has been selected
    }
}
// last line