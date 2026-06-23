package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.talentboozt.edu_service.domains.edu.model.EProfiles;

import java.util.Optional;

@Repository
public interface EProfilesRepository extends MongoRepository<EProfiles, String> {
    Optional<EProfiles> findByUserId(String userId);
    void deleteByUserId(String userId);
}
