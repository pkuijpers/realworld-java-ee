package nl.piq.realworldjavaee.domain;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

class Password {
    private static final int ITERATIONS = 5;
    private static final int KEY_LENGTH = 256;

    private byte[] passwordHash;
    private byte[] salt;

    Password(String password) {
        setSaltAndPasswordHash(password);
    }

    boolean matches(String password) {
        if (password == null) return false;
        char[] pw = password.toCharArray();
        byte[] hash = doHashPassword(pw, salt);
        return Arrays.equals(passwordHash, hash);
    }

    private void setSaltAndPasswordHash(String password) {
        char[] pw = password.toCharArray();
        byte[] salt = new byte[32];
        Random random = new SecureRandom();
        random.nextBytes(salt);
        this.salt = salt;
        this.passwordHash = doHashPassword(pw, salt);
    }

    private static byte[] doHashPassword(final char[] password, final byte[] salt) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(password, salt, Password.ITERATIONS, Password.KEY_LENGTH);
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
