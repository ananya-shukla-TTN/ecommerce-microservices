package com.ttn.consumer.service;

import com.ttn.consumer.security.AESEncryptionUtil;
import com.ttn.consumer.security.KeyLoaderUtil;
import com.ttn.consumer.security.RSAEncryptionUtil;
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

    public Map<String, String> encryptForProducer(String data) throws Exception {
        SecretKey aesKey = aesEncryptionUtil.generateAESKey();
        String encryptedData = aesEncryptionUtil.encrypt(data, aesKey);
        String encryptedKey = RSAEncryptionUtil.encryptAESKeyWithRSA(aesKey,
                keyLoaderUtil.loadProducerPublicKey());

        Map<String, String> encryptedPayload = new HashMap<>();
        encryptedPayload.put("encryptedData", encryptedData);
        encryptedPayload.put("encryptedKey", encryptedKey);
        System.out.println("Encrypted payload: " + encryptedPayload);
        return encryptedPayload;
    }

    public String decryptFromProducer(Map<String, String> encryptedMessage) throws Exception {
        SecretKey aesKey = RSAEncryptionUtil.decryptAESKeyWithRSA(encryptedMessage.get("encryptedKey"),
                keyLoaderUtil.loadConsumerPrivateKey());
        System.out.println("Decrypted data:" +
                aesEncryptionUtil.decrypt(encryptedMessage.get("encryptedData"), aesKey));
        return aesEncryptionUtil.decrypt(encryptedMessage.get("encryptedData"), aesKey);
    }
}
