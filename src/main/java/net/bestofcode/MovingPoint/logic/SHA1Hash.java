package net.bestofcode.MovingPoint.logic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public void print() {
        System.out.println(this.encryptedInput);
    }
} 
