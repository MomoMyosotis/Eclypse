// first line

package app.helpers.mail;
import app.config.db.DbHandler.Mail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class SessionHandler {
    private SessionHandler(){}

    public static ArrayList<MailAccount> init(){
        ArrayList<MailAccount> disco = discover();
        ArrayList<MailAccount> quack = new ArrayList<>(); // TO USE
        if (disco == null){
            return disco;
        }
        try {
            for (MailAccount i : disco){
                if (configured(i)){
                    quack.add(i); // keep only usable accounts
                }
            }
        } catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        return quack;
    }

    private static boolean configured(MailAccount ma){
            System.out.println("configured()");
        if (!ma.hasData()){
            return false;
        }
        if(!ma.authenticate()){
            return false;
        }
        return true;
    }

    private static ArrayList<MailAccount> discover(){
        try {
        ArrayList<MailAccount> res = Mail.usable() ;
            if (res == null || res.isEmpty()){
                System.out.println("res is empty =(");
                MailAccount.create();
                discover();
            } else{
                DP(res);
            }
        return res;
        }  catch (SQLException a){
            a.printStackTrace();
            return null;
        }
    }

    // DP -> debug printer
    private static void DP(ArrayList<MailAccount> gioie){
            System.out.println("DP()");
        for (MailAccount q : gioie){
            System.out.println(q);
        }
    }
}
// last line