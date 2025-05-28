package com.ttn.producer.repository;

import com.ttn.producer.model.EncryptionMode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptionModeRepository extends MongoRepository<EncryptionMode, String> {
}
