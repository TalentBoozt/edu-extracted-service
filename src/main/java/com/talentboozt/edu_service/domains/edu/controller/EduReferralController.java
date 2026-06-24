package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/api/v2/referrals")
public class EduReferralController {

    @PostMapping("/code/generate")
    public ResponseEntity<ReferralCode> generateMyCode(@AuthenticatedUser String userId) {
        String shortId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        ReferralCode mockCode = new ReferralCode(
            UUID.randomUUID().toString(),
            "REF-" + shortId,
            userId,
            Instant.now()
        );
        return ResponseEntity.ok(mockCode);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ReferralCode> validateCode(@PathVariable String code) {
        ReferralCode mockCode = new ReferralCode(
            UUID.randomUUID().toString(),
            code,
            "mock-user-id",
            Instant.now()
        );
        return ResponseEntity.ok(mockCode);
    }

    @PostMapping("/register")
    public ResponseEntity<Referral> register(@AuthenticatedUser String userId, 
                                           @RequestParam String code, 
                                           @RequestParam String type) {
        Referral mockReferral = new Referral(
            UUID.randomUUID().toString(),
            "mock-referrer-id",
            userId,
            type,
            "PENDING",
            false,
            Instant.now(),
            null
        );
        return ResponseEntity.ok(mockReferral);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Referral>> getMyReferrals(@AuthenticatedUser String userId) {
        return ResponseEntity.ok(Collections.emptyList());
    }

    // Static nested DTOs for wire compatibility
    public static record ReferralCode(String id, String code, String userId, Instant createdAt) {}

    public static record Referral(
        String id,
        String referrerId,
        String referredUserId,
        String type,
        String status,
        boolean rewardIssued,
        Instant createdAt,
        Instant completedAt
    ) {}
}
