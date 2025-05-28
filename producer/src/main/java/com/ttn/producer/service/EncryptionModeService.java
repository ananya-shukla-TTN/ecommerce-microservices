package com.ttn.producer.service;

import com.ttn.producer.model.EncryptionMode;
import com.ttn.producer.repository.EncryptionModeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionModeService {
    private final EncryptionModeRepository encryptionModeRepository;

    public boolean isGCMModeEnabled() {
        return encryptionModeRepository.findAll()
                .stream()
                .findFirst()
                .map(encryptionMode -> encryptionMode.getIsGCMEnabled())
                .orElse(false);
    }

    public void add(EncryptionMode encryptionMode){
        encryptionModeRepository.save(encryptionMode);
    }
}
