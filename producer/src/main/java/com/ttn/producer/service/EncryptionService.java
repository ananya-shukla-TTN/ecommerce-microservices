package com.ttn.producer.service;

import com.ttn.producer.security.AESEncryptionUtil;
import com.ttn.producer.security.KeyLoaderUtil;
import com.ttn.producer.security.RSAEncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EncryptionService {
    private final KeyLoaderUtil keyLoaderUtil;
    private final AESEncryptionUtil aesEncryptionUtil;

    public Map<String, String> encryptForConsumer(String data) throws Exception {
        SecretKey aesKey = aesEncryptionUtil.generateAESKey();
        String encryptedData = aesEncryptionUtil.encrypt(data, aesKey);
        String encryptedKey = RSAEncryptionUtil.encryptAESKeyWithRSA(aesKey, keyLoaderUtil.loadConsumerPublicKey());

        Map<String, String> response = new HashMap<>();
        response.put("encryptedData", encryptedData);
        response.put("encryptedKey", encryptedKey);
        System.out.println("Encrypted data: " + response);
        return response;
    }

    public String decryptFromConsumer(Map<String, String> encryptedRequest) throws Exception {
        String encryptedData = encryptedRequest.get("encryptedData");
        String encryptedKey = encryptedRequest.get("encryptedKey");

        SecretKey aesKey = RSAEncryptionUtil.decryptAESKeyWithRSA(encryptedKey, keyLoaderUtil.loadProducerPrivateKey());
        System.out.println("Decrypted data: " + aesEncryptionUtil.decrypt(encryptedData, aesKey));
        return aesEncryptionUtil.decrypt(encryptedData, aesKey);
    }
}