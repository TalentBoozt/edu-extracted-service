package com.talentboozt.edu_service.domains.edu.acl;

/**
 * Anti-Corruption Layer port for Referral integration.
 * Decouples the EDU domain from the portal's ReferralService.
 */
public interface ReferralPort {

    /**
     * Called when a course purchase is finalized to trigger referral completion.
     */
    void handlePurchaseFinalization(String buyerId);

    /**
     * Returns the referrer's commission share from a creator's sale.
     */
    double getReferrerCommissionShare(String creatorId, double totalAmount);

    /**
     * Distributes creator referral commission to the referrer.
     */
    void distributeCreatorReferralCommission(String creatorId, double amount, String transactionId);
}
