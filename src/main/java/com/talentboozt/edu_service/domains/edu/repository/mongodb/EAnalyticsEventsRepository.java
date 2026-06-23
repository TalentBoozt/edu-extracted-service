package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.talentboozt.edu_service.domains.edu.model.EAnalyticsEvents;
import java.util.List;

@Repository
public interface EAnalyticsEventsRepository extends MongoRepository<EAnalyticsEvents, String> {
    List<EAnalyticsEvents> findByCourseId(String courseId);
    List<EAnalyticsEvents> findByUserId(String userId);
    List<EAnalyticsEvents> findByUserIdOrderByTimestampDesc(String userId);
    long countByType(com.talentboozt.edu_service.domains.edu.enums.EAnalyticsEvent type);
}
