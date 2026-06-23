package com.talentboozt.edu_service.domains.edu.dto.workspace;

import lombok.Data;
import java.util.List;

@Data
public class LearningPathRequest {
    private String title;
    private String description;
    private List<String> orderedCourseIds;
}
