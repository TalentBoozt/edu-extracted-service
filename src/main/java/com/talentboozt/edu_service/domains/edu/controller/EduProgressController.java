package com.talentboozt.edu_service.domains.edu.controller;

import com.talentboozt.edu_service.domains.edu.model.EEnrollments;
import com.talentboozt.edu_service.domains.edu.service.EduProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/edu/progress")
public class EduProgressController {

    private final EduProgressService progressService;

    public EduProgressController(EduProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("/resume/user/{userId}/course/{courseId}")
    @PreAuthorize("hasAuthority('LEARNER') or hasAuthority('ENTERPRISE_INSTRUCTOR')")
    public ResponseEntity<EEnrollments> resumeLearning(
            @PathVariable String userId,
            @PathVariable String courseId) {
        return ResponseEntity.ok(progressService.resumeLearning(userId, courseId));
    }

    @PutMapping("/streak/user/{userId}")
    @PreAuthorize("hasAuthority('LEARNER')")
    public ResponseEntity<Void> trackLearningStreak(@PathVariable String userId) {
        progressService.trackLearningStreak(userId);
        return ResponseEntity.ok().build();
    }
}
