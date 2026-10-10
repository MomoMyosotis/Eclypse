// first line

package app.platform;
import java.time.LocalDateTime;
import app.helpers.events.InputReader;

public class Boopinator {
    private String secret;
    private LocalDateTime expiresAt;

    public Boopinator(String secret, LocalDateTime expiresAt){
        this.secret = secret;
        this.expiresAt = expiresAt;
    }

    public boolean create(String given){
        // TODO
        return false;
    }

    public LocalDateTime ExpiresAT(){
        return expiresAt;
    }

    public boolean isValid(){
        return expiresAt != null && LocalDateTime.now().isBefore(expiresAt);
    }

    public boolean login(String given) throws NullPointerException{
        if (given == null){
            System.out.print("pwsd: ");
            given = InputReader.WhatTs();
            System.out.println();
        }
        return given.equals(secret);
    }

    public boolean authorize(String given){
        if (isValid()){
            return true;
        }
        if (!login(given)){
            return false;
        }
        expiresAt = LocalDateTime.now().plusHours(2);
        return true;
    }

    public boolean change(String key){
        // TODO
        return false;
    }
}
// last line