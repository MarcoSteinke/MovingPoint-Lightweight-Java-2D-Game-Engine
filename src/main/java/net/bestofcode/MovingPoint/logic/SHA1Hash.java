package net.bestofcode.MovingPoint.logic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class SHA1Hash {

    private final String encryptedInput;

    public SHA1Hash(String input) {
        this.encryptedInput = encrypt(input);
    }

    private static String encrypt(String input) throws RuntimeException {

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

            byte[] messageDigestAsBytes = messageDigest.digest(input.getBytes());

            BigInteger bigInteger = new BigInteger(1, messageDigestAsBytes);

            String hashtext = bigInteger.toString(16);

            while (hashtext.length() < 32)
                hashtext = "0" + hashtext;

            return hashtext;

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e);

        }
    }

    // Driver code
    public static void main(String[] args) {

        new SHA1Hash("Polizeihunde").print();

    }

    public String getEncryptedInput() {

        return this.encryptedInput;
    }

    @Override
    public boolean equals(Object externalSHA1Hash) {
        if (this == externalSHA1Hash) return true;
        if (externalSHA1Hash == null || getClass() != externalSHA1Hash.getClass()) return false;

        SHA1Hash sha1Hash = (SHA1Hash) externalSHA1Hash;

        return this.encryptedInput.equals(((SHA1Hash) externalSHA1Hash).encryptedInput);
    }

    @Override
    public int hashCode() {
        return encryptedInput != null ? encryptedInput.hashCode() : 0;
    }

    public void print() {
        System.out.println(this.encryptedInput);
    }
} 
