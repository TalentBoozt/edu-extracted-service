package com.talentboozt.edu_service.domains.edu.dto.workspace;

import com.talentboozt.edu_service.domains.edu.enums.EWorkspaceType;
import com.talentboozt.edu_service.domains.edu.enums.ESubscriptionPlan;
import lombok.Data;

@Data
public class WorkspaceRequest {
    private String name;
    private String description;
    private EWorkspaceType type;
    private ESubscriptionPlan plan;
    private String domain;
}
