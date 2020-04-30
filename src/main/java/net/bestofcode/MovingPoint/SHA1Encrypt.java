package net.bestofcode.MovingPoint;

import java.math.BigInteger;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

public class SHA1Encrypt { 

    private String encryptedInput;

    public SHA1Encrypt(String input){
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
            
		} 

		catch (NoSuchAlgorithmException e) {

            throw new RuntimeException(e); 
            
		} 
    } 
    
    public String getEncryptedInput(){
        
        return this.encryptedInput;
    }

    public void print() {
        System.out.println(this.encryptedInput);
    }

	// Driver code 
	public static void main(String args[]) { 

        new SHA1Encrypt("Polizeihunde").print();
        
	} 
} 
