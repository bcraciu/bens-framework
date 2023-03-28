package com.spotify.utilities;

public class EncryptAndDecrypt {
//        public static void keyGeneration(String[] args) throws NoSuchAlgorithmException {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            SecureRandom secureRandom = new SecureRandom();
//            int keyBitSize = 256;
//            keyGenerator.init(keyBitSize, secureRandom);
//            SecretKey secretKey = keyGenerator.generateKey();
//            System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
//        }

//    public static void main(String[] args) {
//
//            String data = "This is not easy as you think";
//            String key = "---------------------------------";
//            String encrypted = encrypt(key, data);
//            System.out.println(encrypted);
//            System.out.println(decrypt(key, encrypted));
//        }

//        public static String encrypt(final String secret, final String data) {
//            byte[] decodedKey = Base64.getDecoder().decode(secret);
//            try {
//                Cipher cipher = Cipher.getInstance("AES");
//                // rebuild key using SecretKeySpec
//                SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");//
//                cipher.init(Cipher.ENCRYPT_MODE, originalKey);
//                byte[] cipherText = cipher.doFinal(data.getBytes("UTF-8"));
//                return Base64.getEncoder().encodeToString(cipherText);
//            } catch (Exception e) {
//                throw new RuntimeException(
//                        "Error occured while encrypting data", e);
//            }
//        }
//
//        public static String decrypt(final String secret,
//                                     final String encryptedString) {
//
//            byte[] decodedKey = Base64.getDecoder().decode(secret);
//            try {
//                Cipher cipher = Cipher.getInstance("AES");
//                // rebuild key using SecretKeySpec
//                SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
//                cipher.init(Cipher.DECRYPT_MODE, originalKey);
//                byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
//                return new String(cipherText);
//            } catch (Exception e) {
//                throw new RuntimeException(
//                        "Error occured while decrypting data", e);
//            }
//        }
//
//
    }

