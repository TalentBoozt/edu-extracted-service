package com.talentboozt.edu_service.domains.edu.dto.moderation;

import lombok.Data;
import com.talentboozt.edu_service.domains.edu.enums.EReportReason;

@Data
public class ReportRequest {
    private String reporterId;
    private String targetEntityId;
    private String entityType; // COURSE, USER, REVIEW
    private EReportReason reason;
    private String description;
}
