package com.ttn.producer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "encryption_mode")
@Getter
@Setter
public class EncryptionMode {
    @Id
    private String id;

    private Boolean isGCMEnabled;
}

