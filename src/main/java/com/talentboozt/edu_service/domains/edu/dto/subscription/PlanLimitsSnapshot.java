package com.talentboozt.edu_service.domains.edu.dto.subscription;

import java.util.List;

public record PlanLimitsSnapshot(
        int maxCourses,
        int aiCreditsPerMonth,
        int maxAiGenerationsPerMonth,
        int validationCreditsPerMonth,
        int hourlyAiLimit,
        int dailyAiLimit,
        double commissionRate,
        List<String> features) {

    public PlanLimitsSnapshot {
        features = features == null ? List.of() : List.copyOf(features);
    }
}
