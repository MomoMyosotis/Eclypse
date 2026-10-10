// first line

package app.helpers.mail.PMA;
import app.helpers.general.Vuoto;
import app.helpers.mail.MailAccount;

public class PswdMailAccount extends MailAccount{
    
    public PswdMailAccount(
        int id,
        String provider,
        String adress,
        String accountIdentifier,
        String service,
        String authMethod,
        String username,
        String credentialReference){
            super(id, provider, adress, accountIdentifier, service, "APP_PASSWORD", username, credentialReference);
        }

    @Override
    public boolean hasData(){
        if (Vuoto.v(getCR()) || Vuoto.v(getAddress()) || Vuoto.v(getProvider())){
            return false;
        }
        return true;
    }

    @Override
    public boolean authenticate(){
        // TODO
        return false;
    }

    @Override
    public boolean renew(){
        // TODO
        return true;
    }
}
// last line