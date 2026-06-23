package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.model.EnterpriseInquiry;
import com.talentboozt.edu_service.domains.edu.service.EduEnterpriseService;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/edu/enterprise")
@RequiredArgsConstructor
public class EduEnterpriseController {
    private final EduEnterpriseService enterpriseService;

    @PostMapping("/inquiry")
    public ResponseEntity<EnterpriseInquiry> submitInquiry(
            @AuthenticatedUser String userId,
            @RequestBody EnterpriseInquiry inquiry) {
        inquiry.setUserId(userId);
        return ResponseEntity.ok(enterpriseService.submitInquiry(inquiry));
    }

    @GetMapping("/admin/inquiries")
    @PreAuthorize("hasAuthority('PLATFORM_ADMIN')")
    public ResponseEntity<List<EnterpriseInquiry>> getInquiries() {
        return ResponseEntity.ok(enterpriseService.getAllInquiries());
    }

    @PutMapping("/admin/inquiries/{id}/approve")
    @PreAuthorize("hasAuthority('PLATFORM_ADMIN')")
    public ResponseEntity<EnterpriseInquiry> approveInquiry(
            @PathVariable String id,
            @AuthenticatedUser String adminId,
            @RequestBody Map<String, Object> config) {
        return ResponseEntity.ok(enterpriseService.approveInquiry(id, adminId, config));
    }

    @PutMapping("/admin/inquiries/{id}/reject")
    @PreAuthorize("hasAuthority('PLATFORM_ADMIN')")
    public ResponseEntity<EnterpriseInquiry> rejectInquiry(
            @PathVariable String id,
            @AuthenticatedUser String adminId,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(enterpriseService.rejectInquiry(id, adminId, body.get("reason")));
    }
}
