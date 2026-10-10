// first line

package app.helpers.mail;

import java.sql.SQLException;

import app.config.db.DbHandler;
import app.helpers.events.InputReader;
import app.helpers.mail.Oauth.OAuth;
import app.helpers.mail.PMA.PswdMailAccount;

public abstract class MailAccount {

/*
id -> Che l'account esista nel DB.
provider -> Che il provider sia riconosciuto o che sia supportato tramite configurazione generica.
service -> Se necessario, che il servizio o endpoint sia configurato correttamente.
address -> Che l'indirizzo email abbia un formato plausibile.
accountidentifier -> Se richiesto dal provider, che sia presente.
username -> Che sia presente se il metodo di autenticazione lo richiede.
authmethod -> Che il metodo sia supportato, ad esempio OAUTH2 o APP_PASSWORD.
credential_reference -> Che il riferimento alle credenziali esista, se utilizzato.
access_token -> Che esista e sia ancora utilizzabile, se l'autenticazione usa OAuth 2.0.
refresh_token -> Che sia disponibile quando serve rinnovare l'access token.
expires_at -> Che la scadenza sia interpretabile e coerente con il token, se applicabile.
scopes -> Che i permessi richiesti siano sufficienti per le operazioni desiderate, con OAuth 2.0.
*/
    private int id;
    // Identità
    private String provider;
    private String address;
    private String accountIdentifier;
    // Connessione
    private String service;
    // Autenticazione
    private String authMethod;
    private String username;
    private String credentialReference;

    public MailAccount(int id,
        String provider,
        String adress,
        String accountIdentifier,
        String service,
        String authMethod,
        String username,
        String credentialReference){
        this.id = id;
        this.provider = provider;
        this.address = adress;
        this.accountIdentifier = accountIdentifier;
        this.service = service;
        this.authMethod = authMethod;
        this.username = username;
        this.credentialReference = credentialReference;
    }

    public int getId(){
        return id;
    }
    public String getProvider(){
        return provider;
    }
    public String getAddress(){
        return address;
    }
    public String getAI(){
        return accountIdentifier;
    }
    public String getService(){
        return service;
    }
    public String getAM(){
        return authMethod;
    }
    public String getUsername(){
        return username;
    }
    public String getCR(){
        return credentialReference;
    }

    public static MailAccount create(){

        System.out.print("provider: ");
        String provider = InputReader.WhatTs();
        System.out.print("\nEmail Adress: ");
        String email = InputReader.WhatTs();
        System.out.print("\nAuthentication method(1. OAuth 2.0 - 2. app password)\nchoice: ");

        int choice;
        while (true){
            choice = InputReader.WhatTsInt();
            if (choice == 1 || choice == 2){
                break;
            }
            System.out.println("\ninvalid input. " + choice);
        }
        String auth = (choice == 1) ? "OAUTH2" : "APP_PASSWORD";
        String username  = (email.contains("@")) ? email.substring(0, email.indexOf("@")) : email;
        MailAccount ma;
        if (choice == 1){
            ma = new OAuth(0,
                provider,
                email,
                null, // account identifier
                null, // servie
                auth,
                username,
                null, // credential reference
                null, // accesstoken
                null, // refreshtoken
                null, // expires at
                null // scopes
            );
        } else {
            ma = new PswdMailAccount(
                0,
                provider,
                email,
                null, // account identifier
                null, // servie
                auth,
                username,
                null // credential reference
            );
        }
        try {
            int id = DbHandler.Mail.create(ma);
            return DbHandler.Mail.read(id);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public abstract boolean hasData();

    public abstract boolean authenticate();

    public abstract boolean renew();
}
// last line