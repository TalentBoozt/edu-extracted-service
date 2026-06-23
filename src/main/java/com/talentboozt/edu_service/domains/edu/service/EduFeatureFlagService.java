package com.talentboozt.edu_service.domains.edu.service;

import com.talentboozt.edu_service.domains.edu.dto.plan.LimitConfig;
import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.EUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * EDU-local feature flag service.
 * Replaces the shared SubscriptionFeatureFlagService with EDU-specific
 * plan resolution using PlanConfigService.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EduFeatureFlagService {

    private final PlanConfigService planConfigService;
    private final EUserRepository userRepository;

    /**
     * Checks if a feature is enabled for a specific user based on their EDU subscription plan.
     */
    public boolean isFeatureEnabled(String userId, String featureKey) {
        ESubscriptionPlan plan = resolveUserPlan(userId);
        return isFeatureEnabledForPlan(plan, featureKey);
    }

    /**
     * Internal check for plan-based feature enabling.
     */
    public boolean isFeatureEnabledForPlan(ESubscriptionPlan plan, String featureKey) {
        return getFeaturesForPlan(plan).contains(featureKey);
    }

    /**
     * Retrieves all enabled feature keys for a specific plan.
     */
    public List<String> getFeaturesForPlan(ESubscriptionPlan plan) {
        ESubscriptionPlan effective = plan != null ? plan : ESubscriptionPlan.FREE;
        LimitConfig limits = planConfigService.getPlanLimits(effective);
        return limits.getFeatures() != null ? limits.getFeatures() : Collections.emptyList();
    }

    private ESubscriptionPlan resolveUserPlan(String userId) {
        return userRepository.findById(userId)
                .map(user -> user.getPlan() != null ? user.getPlan() : ESubscriptionPlan.FREE)
                .orElse(ESubscriptionPlan.FREE);
    }
}
