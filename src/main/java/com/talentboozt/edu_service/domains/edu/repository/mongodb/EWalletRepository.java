package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import com.talentboozt.edu_service.domains.edu.model.EWallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EWalletRepository extends MongoRepository<EWallet, String> {
    Optional<EWallet> findByUserId(String userId);
}
