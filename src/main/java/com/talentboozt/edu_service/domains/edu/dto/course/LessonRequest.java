package com.talentboozt.edu_service.domains.edu.dto.course;

import com.talentboozt.edu_service.domains.edu.enums.ELessonType;
import lombok.Data;

@Data
public class LessonRequest {
    private String title;
    private String description;
    private ELessonType type;
    private String contentUrl;
    private String textContent;
    private String markdownContent;
    private Integer duration;
    private Boolean isFreePreview;
    private Integer order;
    private String videoThumbnail;
    private String[] attachments;
}
