package com.talentboozt.edu_service.domains.edu.dto.enrollment;

import com.talentboozt.edu_service.domains.edu.enums.EPaymentMethod;
import lombok.Data;

@Data
public class EnrollmentRequest {
    private String courseId;
    private String source; // e.g. MARKETPLACE, ENTERPRISE, COUPON
    private String paymentGatewayId;
    private EPaymentMethod paymentMethod;
}
