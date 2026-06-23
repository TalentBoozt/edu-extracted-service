package com.talentboozt.edu_service.domains.edu.dto.course;

import lombok.Data;
import java.util.List;

@Data
public class OrderUpdateRequest {
    private List<String> orderedIds;
}
