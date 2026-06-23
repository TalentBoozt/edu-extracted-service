package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.enums.ECourseValidationStatus;
import com.talentboozt.edu_service.domains.edu.model.ECourseSections;
import com.talentboozt.edu_service.domains.edu.model.ECourses;
import com.talentboozt.edu_service.domains.edu.model.ELessons;
import com.talentboozt.edu_service.domains.edu.model.EValidationReports;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.ECourseSectionsRepository;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.ECoursesRepository;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.ELessonsRepository;
import com.talentboozt.edu_service.domains.edu.repository.mongodb.EValidationReportsRepository;
import com.talentboozt.edu_service.domains.edu.service.EduAuditService;
import com.talentboozt.edu_service.shared.security.annotations.AuthenticatedUser;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/edu/admin/reviews")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('PLATFORM_ADMIN')")
public class EduAdminReviewController {

    private final ECoursesRepository coursesRepository;
    private final ECourseSectionsRepository sectionsRepository;
    private final ELessonsRepository lessonsRepository;
    private final EValidationReportsRepository validationRepository;
    private final EduAuditService auditService;

    @GetMapping("/pending")
    public ResponseEntity<List<ECourses>> getPendingReviews() {
        return ResponseEntity.ok(coursesRepository.findByValidationStatusIn(
                List.of(ECourseValidationStatus.MANUAL_PENDING, ECourseValidationStatus.AI_APPROVED)));
    }

    @GetMapping("/{courseId}/details")
    public ResponseEntity<Map<String, Object>> getReviewDetails(@PathVariable String courseId) {
        ECourses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<ECourseSections> sections = sectionsRepository.findByCourseId(courseId);
        List<ELessons> lessons = lessonsRepository.findByCourseId(courseId);
        EValidationReports lastValidation = validationRepository.findFirstByCourseIdOrderByCreatedAtDesc(courseId)
                .orElse(null);

        Map<String, Object> details = new HashMap<>();
        details.put("course", course);
        details.put("sections", sections);
        details.put("lessons", lessons);
        details.put("aiReport", lastValidation);

        return ResponseEntity.ok(details);
    }

    @PostMapping("/{courseId}/approve")
    public ResponseEntity<ECourses> approveCourse(@PathVariable String courseId, @AuthenticatedUser String adminId,
            HttpServletRequest request) {
        ECourses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ECourseValidationStatus oldStatus = course.getValidationStatus();
        course.setValidationStatus(ECourseValidationStatus.VALIDATED);
        course.setTalnovaVerified(true);
        course.setUpdatedAt(Instant.now());

        ECourses saved = coursesRepository.save(course);

        auditService.logAction(adminId, "APPROVE_COURSE", courseId, "COURSE", oldStatus,
                ECourseValidationStatus.VALIDATED, request);

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{courseId}/reject")
    public ResponseEntity<ECourses> rejectCourse(
            @PathVariable String courseId,
            @AuthenticatedUser String adminId,
            @RequestParam String reason,
            HttpServletRequest request) {
        ECourses course = coursesRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        ECourseValidationStatus oldStatus = course.getValidationStatus();
        course.setValidationStatus(ECourseValidationStatus.REJECTED);
        course.setModerationRejectionReason(reason);
        course.setUpdatedAt(Instant.now());

        ECourses saved = coursesRepository.save(course);

        auditService.logAction(adminId, "REJECT_COURSE", courseId, "COURSE", oldStatus,
                ECourseValidationStatus.REJECTED, request);

        return ResponseEntity.ok(saved);
    }
}
