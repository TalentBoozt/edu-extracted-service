package com.talentboozt.edu_service.domains.edu.dto.evaluation;

import lombok.Data;
import java.util.Map;

@Data
public class QuizAttemptRequest {
    private Map<String, String[]> userAnswers;
}
