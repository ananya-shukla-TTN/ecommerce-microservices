package com.ttn.consumer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "encryption_mode")
public class EncryptionMode {
    private Boolean isGCMEnabled;
}
