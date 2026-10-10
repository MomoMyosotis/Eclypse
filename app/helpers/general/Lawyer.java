// first line

package app.helpers.general;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

public class Lawyer {
    private Lawyer(){}

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int NONCE_SIZE = 12;
    private static final int TAG_SIZE = 128;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String[] encrypt(String te) throws GeneralSecurityException{
        // key generator
        KeyGenerator genie = KeyGenerator.getInstance("AES");
        genie.init(KEY_SIZE);
        SecretKey v = genie.generateKey();

        // arndom nonce generator
        byte[] nonce = new byte[NONCE_SIZE];
        RANDOM.nextBytes(nonce);

        // ENCRYPT
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, v, new GCMParameterSpec(TAG_SIZE, nonce));

        byte[] encrypted = c.doFinal(te.getBytes(StandardCharsets.UTF_8));
        byte[] merged = ByteBuffer.allocate(nonce.length + encrypted.length).put(nonce).put(encrypted).array();
        return new String[]{
            Base64.getEncoder().encodeToString(merged),
            Base64.getEncoder().encodeToString(v.getEncoded())
        };
    }

    public static String decrypt(String[] td) throws GeneralSecurityException{
        if (td == null || td.length != 2 || td[0] == null || td[1] == null){
            throw new IllegalArgumentException("decrypt() takes array[encrypted text, key]");
        }

        byte[] combined = Base64.getDecoder().decode(td[0]);
        byte[] key = Base64.getDecoder().decode(td[1]);
        if(combined.length < NONCE_SIZE + TAG_SIZE / 8){
            throw new IllegalArgumentException("\ninvalid encrypted data");
        }

        // extract nonce and cyphretext
        ByteBuffer bl =ByteBuffer.wrap(combined);
        byte[] nonce = new byte[NONCE_SIZE];
        bl.get(nonce);
        byte[] encry = new byte[bl.remaining()];
        bl.get(encry);

        SecretKey secretary = new SecretKeySpec(key, "AES");

        // DECRYPT
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, secretary, new GCMParameterSpec(TAG_SIZE, nonce));
        byte[] boop = c.doFinal(encry);

        return new String(boop, StandardCharsets.UTF_8);
    }
}

// last line