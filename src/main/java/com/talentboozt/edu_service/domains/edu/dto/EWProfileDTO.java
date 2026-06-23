package com.talentboozt.edu_service.domains.edu.dto;

import com.talentboozt.edu_service.shared.common.dto.SocialLinksDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EWProfileDTO {
    private String name;
    private String description;
    private String status;
    private SocialLinksDTO socialLinks;
    
    // Arrays for channels and threads should be stored in their own MongoDB collections 
    // to avoid unbounded array growth inside EWorkspaces.
}
