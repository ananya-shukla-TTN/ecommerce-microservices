package com.ttn.producer.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class KeyLoaderUtil {

    private static final String RSA_ALGORITHM = "RSA";

    @Value("${consumer.private.key.path}")
    private String consumerPrivateKeyPath;

    @Value("${consumer.public.key.path}")
    private String consumerPublicKeyPath;

    @Value("${producer.public.key.path}")
    private String producerPublicKeyPath;

    public PublicKey loadConsumerPublicKey() throws Exception {
        return loadPublicKey(consumerPublicKeyPath);
    }

    public PrivateKey loadConsumerPrivateKey() throws Exception {
        return loadPrivateKey(consumerPrivateKeyPath);
    }

    public PublicKey loadProducerPublicKey() throws Exception {
        return loadPublicKey(producerPublicKeyPath);
    }

    private PublicKey loadPublicKey(String filePath) throws Exception {
        String pem = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(pem);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(new X509EncodedKeySpec(decoded));
    }

    private PrivateKey loadPrivateKey(String filePath) throws Exception {
        String pem = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");
        byte[] decoded = Base64.getDecoder().decode(pem);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }
}
