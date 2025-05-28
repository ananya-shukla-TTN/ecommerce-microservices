package com.ttn.producer.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESEncryptionUtil {
    private static final String AES_ALGORITHM = "AES";
    private static final int AES_KEY_SIZE = 256;

    public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance(AES_ALGORITHM);
        generator.init(AES_KEY_SIZE);
        return generator.generateKey();
    }

    public static String encrypt(String data, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedDataBase64, SecretKey aesKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedDataBase64);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
