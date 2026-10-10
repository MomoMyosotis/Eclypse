// first line

package app.helpers.mail.Oauth;
import app.helpers.general.Vuoto;
import app.helpers.mail.MailAccount;

public class OAuth extends MailAccount{
        private String accessToken;
        private String refreshToken;
        private String expiresAt;
        private String scopes;

        public OAuth(int id,
        String provider,
        String adress,
        String accountIdentifier,
        String service,
        String authMethod,
        String username,
        String credentialReference,
        String accessToken,
        String refreshToken,
        String expiresAt,
        String scopes){ super(
            id, provider, adress, accountIdentifier, service, "OAUTH2", username, credentialReference
        );
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.scopes = scopes;
    }

    public String getAT(){
        return accessToken;
    }
    public String getRT(){
        return refreshToken;
    }
    public String getEAT(){
        return expiresAt;
    }
    public String getS(){
        return scopes;
    }

    @Override
    public boolean hasData(){
        if (getId() < 1 || Vuoto.v(getAddress())|| Vuoto.v(getAT()) || Vuoto.v(getProvider())){
            return false;
        }
        return true;
    }
    
    @Override
    public boolean authenticate(){
        if (ATU()){// is the token still valid?
            return true;
        }
        if (!Vuoto.v(refreshToken) && renew()){ // can we renew the expired token?
            if(ATU()){
                return true;
            }
        }
        return authorize(); // need new uahtorization
    }

    // ATU -> AccessTokenUsable()
    public boolean ATU(){
        if(Vuoto.v(accessToken) || Vuoto.v(expiresAt)){
            return false;
        }
        try {
            return java.time.Instant.parse(expiresAt).isAfter(java.time.Instant.now().plusSeconds(120));
        } catch (java.time.format.DateTimeParseException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean authorize(){
        // TOOD

        return false;
    }

    @Override
    public boolean renew(){
        // TODO
        return true;
    }
}
// last line