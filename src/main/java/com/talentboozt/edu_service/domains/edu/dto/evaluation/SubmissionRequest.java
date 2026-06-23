package com.talentboozt.edu_service.domains.edu.dto.evaluation;

import lombok.Data;

@Data
public class SubmissionRequest {
    private String content;     
    private String[] attachmentUrls;
}
