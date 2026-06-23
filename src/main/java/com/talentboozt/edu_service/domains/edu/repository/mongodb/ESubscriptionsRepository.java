package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;

import java.util.Optional;

@Repository
public interface ESubscriptionsRepository extends MongoRepository<ESubscriptions, String> {
    Optional<ESubscriptions> findByUserId(String userId);
    Optional<ESubscriptions> findByStripeCustomerId(String stripeCustomerId);
}
