package com.ttn.consumer.repository;

import com.ttn.consumer.model.EncryptionMode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptionModeRepository extends MongoRepository<EncryptionMode, String> {
}
