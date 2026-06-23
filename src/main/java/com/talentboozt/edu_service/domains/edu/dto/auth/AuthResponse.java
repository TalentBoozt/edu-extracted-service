package com.talentboozt.edu_service.domains.edu.dto.auth;

import com.talentboozt.edu_service.domains.edu.model.EUser;
import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private EUser user;
    private ESubscriptionPlan currentPlan;
}
