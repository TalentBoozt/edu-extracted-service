package com.talentboozt.edu_service.domains.edu.dto.course;

import lombok.Data;

@Data
public class SectionRequest {
    private String title;
    private String description;
    private Integer order;
}
