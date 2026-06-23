package com.talentboozt.edu_service.domains.edu.dto.monetization;

import lombok.Data;

@Data
public class CourseCheckoutRequest {
    private String userId;
    private String courseId;
    private String affiliateId;
    /** Optional coupon code to apply at checkout */
    private String couponCode;
}
