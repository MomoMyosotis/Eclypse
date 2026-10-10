package app.config.db;

import app.helpers.mail.MailAccount;
import app.helpers.mail.PMA.PswdMailAccount;

import java.sql.SQLException;


public class T {
    
        public static void main(String[] args) throws SQLException {
    
            // CREATE
            MailAccount account = new PswdMailAccount(
                0,
                "test",
                "test@example.com",
                null,
                null,
                "APP_PASSWORD",
                "test",
                "test-credential"
            );
    
            int id = DbHandler.Mail.create(account);
            System.out.println("Created ID: " + id);
    
            // READ
            MailAccount loaded = DbHandler.Mail.read(id);
            System.out.println("Read: " + loaded.getAddress());
    
            // UPDATE
            MailAccount updated = new PswdMailAccount(
                id,
                "test-updated",
                "updated@example.com",
                null,
                null,
                "APP_PASSWORD",
                "test",
                "test-credential"
            );
    
            System.out.println("Updated: " + DbHandler.Mail.update(updated));
            System.out.println("New address: " +
                DbHandler.Mail.read(id).getAddress());
    
            // DELETE
            System.out.println("Deleted: " + DbHandler.Mail.delete(id));
            System.out.println("After delete: " + DbHandler.Mail.read(id));
        }
    }
