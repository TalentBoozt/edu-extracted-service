package com.talentboozt.edu_service.domains.edu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/event")
public class EduTrackingController {

    @PostMapping("/track")
    public ResponseEntity<Map<String, String>> trackEvent(@RequestBody Map<String, Object> payload) {
        log.info("Mock tracking event received: {}", payload.get("event_type"));
        String mockId = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of("status", "success", "id", mockId));
    }

    @PostMapping("/track/batch")
    public ResponseEntity<Map<String, Object>> trackBatchEvents(@RequestBody Map<String, Object> batchRequest) {
        log.info("Mock batch tracking events received");
        
        List<?> eventsList = (List<?>) batchRequest.get("events");
        int count = eventsList != null ? eventsList.size() : 0;
        
        List<String> mockIds = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mockIds.add(UUID.randomUUID().toString());
        }
        
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "count", count,
            "ids", mockIds
        ));
    }
}
