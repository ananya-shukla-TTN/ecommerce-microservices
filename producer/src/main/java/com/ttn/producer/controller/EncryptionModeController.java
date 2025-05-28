package com.ttn.producer.controller;

import com.ttn.producer.model.EncryptionMode;
import com.ttn.producer.service.EncryptionModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/encryption-mode")
public class EncryptionModeController {
    private final EncryptionModeService encryptionModeService;

    @PostMapping("/update")
    public ResponseEntity<String> addMode(@RequestParam Boolean mode){
        EncryptionMode encryptionMode = new EncryptionMode();
        encryptionMode.setIsGCMEnabled(mode);
        encryptionModeService.add(encryptionMode);
        return new ResponseEntity<>("Mode updated successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> getMode() {
        boolean isGCMEnabled = encryptionModeService.isGCMModeEnabled();
        Map<String, Boolean> response = new HashMap<>();
        response.put("isGCMEnabled", isGCMEnabled);
        return ResponseEntity.ok(response);
    }
}
