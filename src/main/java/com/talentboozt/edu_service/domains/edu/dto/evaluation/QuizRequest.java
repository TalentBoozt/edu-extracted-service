package com.talentboozt.edu_service.domains.edu.dto.evaluation;

import com.talentboozt.edu_service.domains.edu.enums.EQuizType;
import lombok.Data;
import java.util.List;

@Data
public class QuizRequest {
    /** Optional link so learners can fetch the quiz via lesson id. */
    private String lessonId;
    private String title;
    private String description;
    private EQuizType type;
    private Integer durationLimit;
    private Double passingScore;
    private List<EQuestionDTO> questions;
    private Boolean isPublished;
    private Integer allowRetakes;
}
