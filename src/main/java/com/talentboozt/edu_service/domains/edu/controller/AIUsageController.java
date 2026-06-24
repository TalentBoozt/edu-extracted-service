package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import com.talentboozt.edu_service.domains.edu.model.ECreditLedger;
import com.talentboozt.edu_service.domains.edu.model.EAiCredits;
import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;
import com.talentboozt.edu_service.domains.edu.service.EduAICreditService;
import com.talentboozt.edu_service.domains.edu.service.EduSubscriptionService;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai/usage")
public class AIUsageController {

    private final EduAICreditService creditService;
    private final EduSubscriptionService subscriptionService;

    public AIUsageController(EduAICreditService creditService, EduSubscriptionService subscriptionService) {
        this.creditService = creditService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/quota")
    public ResponseEntity<Map<String, Object>> getMyQuota(@AuthenticatedUser String userId) {
        ESubscriptions subscription = subscriptionService.getUserSubscription(userId);
        ESubscriptionPlan plan = subscription.getPlan() != null ? subscription.getPlan() : ESubscriptionPlan.FREE;
        EAiCredits credits = creditService.getQuota(userId, plan);
        
        int monthlyLimit = credits.getMonthlyLimit() != null ? credits.getMonthlyLimit() : 0;
        int balance = credits.getBalance() != null ? credits.getBalance() : 0;
        Instant resetDate = credits.getLastResetDate() != null 
                ? credits.getLastResetDate().plus(30, ChronoUnit.DAYS) 
                : Instant.now();
                
        return ResponseEntity.ok(Map.of(
            "used", monthlyLimit - balance,
            "monthlyLimit", monthlyLimit,
            "remaining", balance,
            "resetDate", resetDate
        ));
    }

    @GetMapping("/logs")
    public ResponseEntity<List<ECreditLedger>> getMyUsageLogs(@AuthenticatedUser String userId) {
        return ResponseEntity.ok(creditService.getCreditLedger(userId));
    }
}
