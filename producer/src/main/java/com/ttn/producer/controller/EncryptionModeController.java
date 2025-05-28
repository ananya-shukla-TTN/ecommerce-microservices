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
    public ResponseEntity<String> updateMode(@RequestParam Boolean mode){
        encryptionModeService.updateMode(mode);
        return ResponseEntity.ok("Mode updated successfully");
    }

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> getMode() {
        boolean isGCMEnabled = encryptionModeService.isGCMModeEnabled();
        Map<String, Boolean> response = new HashMap<>();
        response.put("isGCMEnabled", isGCMEnabled);
        return ResponseEntity.ok(response);
    }
}
