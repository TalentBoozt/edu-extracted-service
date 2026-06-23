package com.talentboozt.edu_service.domains.edu.infrastructure.persistence;

import com.talentboozt.edu_service.domains.edu.domain.repository.EduSubscriptionsPersistencePort;
import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.ESubscriptionsRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MongoEduSubscriptionsPersistenceAdapter implements EduSubscriptionsPersistencePort {

    private final ESubscriptionsRepository delegate;

    public MongoEduSubscriptionsPersistenceAdapter(ESubscriptionsRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<ESubscriptions> findByStripeCustomerId(String stripeCustomerId) {
        return delegate.findByStripeCustomerId(stripeCustomerId);
    }

    @Override
    public Optional<ESubscriptions> findByUserId(String userId) {
        return delegate.findByUserId(userId);
    }

    @Override
    public ESubscriptions save(ESubscriptions subscription) {
        return delegate.save(subscription);
    }
}
