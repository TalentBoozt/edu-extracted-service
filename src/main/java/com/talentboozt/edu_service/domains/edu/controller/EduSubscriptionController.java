package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import com.talentboozt.edu_service.domains.edu.model.ESubscriptions;
import com.talentboozt.edu_service.domains.edu.service.EduSubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * EDU Subscription Controller.
 * Routes all subscription operations through EDU's own EduSubscriptionService
 * instead of the shared subscription module.
 */
@RestController
@RequestMapping("/api/edu/subscriptions")
public class EduSubscriptionController {

    private final EduSubscriptionService subscriptionService;

    public EduSubscriptionController(EduSubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ESubscriptions> getSubscription(@PathVariable String userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscription(userId));
    }

    @PutMapping("/{userId}/upgrade")
    @PreAuthorize("hasAuthority('LEARNER') or hasAuthority('ENTERPRISE_INSTRUCTOR') or hasAuthority('SELLER_FREE')")
    public ResponseEntity<ESubscriptions> upgradePlan(@PathVariable String userId,
            @RequestParam ESubscriptionPlan plan) {
        return ResponseEntity.ok(subscriptionService.upgradePlan(userId, plan));
    }

    @PutMapping("/{userId}/cancel")
    @PreAuthorize("hasAuthority('LEARNER') or hasAuthority('ENTERPRISE_INSTRUCTOR') or hasAuthority('SELLER_FREE')")
    public ResponseEntity<Void> cancelSubscription(@PathVariable String userId) {
        subscriptionService.cancelSubscription(userId);
        return ResponseEntity.ok().build();
    }
}
