package com.talentboozt.edu_service.domains.edu.acl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Standalone/no-op implementation of ReferralPort.
 * Used when EDU service runs independently without the monolith's referral system.
 */
@Component
@ConditionalOnProperty(name = "edu.referral.enabled", havingValue = "false", matchIfMissing = true)
public class StandaloneReferralAdapter implements ReferralPort {

    private static final Logger log = LoggerFactory.getLogger(StandaloneReferralAdapter.class);

    @Override
    public void handlePurchaseFinalization(String buyerId) {
        log.debug("Standalone mode: referral purchase finalization skipped for buyer {}", buyerId);
    }

    @Override
    public double getReferrerCommissionShare(String creatorId, double totalAmount) {
        return 0.0;
    }

    @Override
    public void distributeCreatorReferralCommission(String creatorId, double amount, String transactionId) {
        log.debug("Standalone mode: referral commission distribution skipped for creator {}", creatorId);
    }
}
