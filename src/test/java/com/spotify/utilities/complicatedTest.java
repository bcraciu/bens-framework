package com.spotify.utilities;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class test {
    static String data = "This is not easy as you think";
    private static Key key;
    private static final int KEY_SIZE = 128;
    static SecureRandom random = new SecureRandom();

    static IvParameterSpec iv = new IvParameterSpec(random.generateSeed(16));


    public static String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(KEY_SIZE, secureRandom);
        key = keyGenerator.generateKey();
        return key.toString();
    }

    public static String encrypt(final String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey, iv);
            byte[] cipherText = cipher.doFinal(generateSecretKey().getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while encrypting data", e);
        }
    }

    public static String decrypt(final String encryptedString) {

        byte[] decodedKey = Base64.getDecoder().decode(encryptedString);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey, iv);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while decrypting data", e);
        }
    }
}

//    public class FileEncrypterDecrypter {
//        private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(FileEncrypterDecrypter.class);
//        private static final String ALGORITHM = "AES";
//        private static final byte[] keyValue = "ADBSJHJS12547896".getBytes();
//        private static Key key;
//
//        public static String decrypt(String encryptedValue) throws Exception {
//            Cipher c = Cipher.getInstance(ALGORITHM);
//            key = new SecretKeySpec(keyValue, ALGORITHM);
//            c.init(Cipher.DECRYPT_MODE, key);
//            byte[] decodedPass = c.doFinal(Base64.getDecoder().decode(encryptedValue));
//            return new String(decodedPass);
////        }   private static Key generateKey() {
////            return new SecretKeySpec(keyValue, ALGORITHM);
////        }
//    }



//    public static void main(String[] args) {
//
//        String data = "This is not easy as you think";
//        String key = "---------------------------------";
//        String encrypted = encrypt(key, data);
//        System.out.println(encrypted);
//        System.out.println(decrypt(key, encrypted));
//    }
//
//        public static void test(String[] args) throws NoSuchAlgorithmException {
//
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            SecureRandom secureRandom = new SecureRandom();
//            int keyBitSize = 256;
//            keyGenerator.init(keyBitSize, secureRandom);
//            SecretKey secretKey = keyGenerator.generateKey();
//            System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
//        }
//
