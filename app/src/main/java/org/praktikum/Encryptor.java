package org.praktikum;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor
{
    private final static String algorithm = "AES";
    private final static String transformation = "AES/ECB/PKCS5Padding";

    public static String generateKey()
    {
        try
        {
            KeyGenerator key = KeyGenerator.getInstance(algorithm);

            SecretKey secret = key.generateKey();
            String encodedKey = Base64.getEncoder().encodeToString(secret.getEncoded());

            return encodedKey;
        }

        catch (NoSuchAlgorithmException nsae)
        {
            return "No " + algorithm + " Algorithm";
        }
    }

    public static String encrypt(String plainText, String key)
    {
        try
        {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);

            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

            return encryptedText;
        }

        catch (Exception e)
        {
            return "Error: " + e;
        }
    }

    public static String decrypt(String encodedText, String key)
    {
        try
        {
            byte[] decodedKey = Base64.getDecoder().decode(key);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);

            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, originalKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(encodedText);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

            return decryptedText;
        }

        catch (Exception e)
        {
            return "Error: " + e;
        }
    }
}
