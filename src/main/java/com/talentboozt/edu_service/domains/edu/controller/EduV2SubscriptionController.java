package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.dto.plan.LimitConfig;
import com.talentboozt.edu_service.domains.edu.dto.subscription.PlanLimitsSnapshot;
import com.talentboozt.edu_service.domains.edu.dto.subscription.SubscriptionResponseDTO;
import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionStatus;
import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;
import com.talentboozt.edu_service.domains.edu.service.EduSubscriptionService;
import com.talentboozt.edu_service.domains.edu.service.PlanConfigService;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v2/subscription")
public class EduV2SubscriptionController {

    private final EduSubscriptionService subscriptionService;
    private final PlanConfigService planConfigService;

    public EduV2SubscriptionController(EduSubscriptionService subscriptionService, PlanConfigService planConfigService) {
        this.subscriptionService = subscriptionService;
        this.planConfigService = planConfigService;
    }

    @GetMapping("/me")
    public ResponseEntity<SubscriptionResponseDTO> getMySubscription(@AuthenticatedUser String userId) {
        ESubscriptions sub = subscriptionService.getUserSubscription(userId);
        
        ESubscriptionPlan plan = sub.getPlan() != null ? sub.getPlan() : ESubscriptionPlan.FREE;
        LimitConfig limits = planConfigService.getPlanLimits(plan);
        
        PlanLimitsSnapshot limitsSnapshot = new PlanLimitsSnapshot(
                limits.getMaxCourses(),
                limits.getAiCreditsPerMonth(),
                limits.getMaxAiGenerationsPerMonth(),
                limits.getValidationCreditsPerMonth(),
                limits.getHourlyAiLimit(),
                limits.getDailyAiLimit(),
                limits.getCommissionRate(),
                limits.getFeatures()
        );
        
        List<String> featuresList = sub.getFeatures() != null ? Arrays.asList(sub.getFeatures()) : limits.getFeatures();
        
        SubscriptionResponseDTO response = SubscriptionResponseDTO.builder()
                .id(sub.getId() != null ? sub.getId() : "default-free")
                .plan(plan)
                .status(sub.getStatus() != null ? sub.getStatus() : ESubscriptionStatus.ACTIVE)
                .expiresAt(sub.getEndDate())
                .features(featuresList)
                .limits(limitsSnapshot)
                .build();
                
        return ResponseEntity.ok(response);
    }
}
