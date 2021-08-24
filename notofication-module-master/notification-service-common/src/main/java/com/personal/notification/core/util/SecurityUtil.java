package com.personal.notification.core.util;


import java.security.SecureRandom;
import java.util.Scanner;
  
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
  
/**
 * 
 * @author dvsnk
**/
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author dvsnk
 *
 */
public class SecurityUtil {
 
	/** secret key **/
    private static SecretKeySpec secretKey;
    
    /** key **/
    private static byte[] key;
    /** security key **/
    public static final String SECURITY_KEY="ssshhhhhhhhhhh!!!!";
 
    /**
     * 
     * @param myKey
     */
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String encrypt(String strToEncrypt) {
        try
        {
            setKey(SECURITY_KEY);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    /**
     * 
     * @param strToDecrypt
     * @param secret
     * @return
     */
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
    
    /**
     * encrypt
     */
    public static void main(String[] args) {
    	String strEncrypted = SecurityUtil.encrypt("Nagendra_72");
    	
    	System.out.println(" ENCRYPTED :::   "+strEncrypted);
    	
    	System.out.println(" ENCRYPTED :::   "+decrypt(strEncrypted, SECURITY_KEY));
    }
}