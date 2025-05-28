package com.ttn.consumer.service;

import com.ttn.consumer.repository.EncryptionModeRepository;
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
}
