package com.talentboozt.edu_service.domains.edu.domain.repository;

import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;

import java.util.Optional;

/**
 * Persistence boundary for LMS subscription documents (renewal + user-scoped lookup/save slice).
 */
public interface EduSubscriptionsPersistencePort {

    Optional<ESubscriptions> findByStripeCustomerId(String stripeCustomerId);

    Optional<ESubscriptions> findByUserId(String userId);

    ESubscriptions save(ESubscriptions subscription);
}
