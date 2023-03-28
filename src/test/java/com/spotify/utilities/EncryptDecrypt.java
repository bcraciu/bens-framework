package com.spotify.utilities;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Possible KEY_SIZE values are 128, 192 and 256
 * Possible T_LEN values are 128, 120, 112, 104 and 96
 */

public class EncryptDecrypt {
    private static final int KEY_SIZE = 128;
    static SecureRandom random = new SecureRandom();
    static IvParameterSpec iv = new IvParameterSpec(random.generateSeed(16));
    private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();
    private static final String ALGORITHM = "AES";
    private static Key key;

    public static Key generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(KEY_SIZE, secureRandom);
        key = keyGenerator.generateKey();
        return key;
    }

    public static String encryptString(String secret) throws Exception {
        byte[] messageInBytes = secret.getBytes();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        // SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(), iv);
        byte[] encryptedBytes = cipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }

    public static String decryptString(String encryptedMessage) throws Exception {
        byte[] messageInBytes = decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

        //SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }

    private static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private static byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

}