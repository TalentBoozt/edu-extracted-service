package com.talentboozt.edu_service.domains.edu.dto.evaluation;

import com.talentboozt.edu_service.domains.edu.enums.EGradingStatus;
import lombok.Data;

@Data
public class GradeRequest {
    private Double score;
    private EGradingStatus status;
    private String feedback;
}
