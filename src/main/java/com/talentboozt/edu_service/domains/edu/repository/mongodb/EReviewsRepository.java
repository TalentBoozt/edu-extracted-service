package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.talentboozt.edu_service.domains.edu.model.EReviews;
import java.util.List;

@Repository
public interface EReviewsRepository extends MongoRepository<EReviews, String> {
    List<EReviews> findByCourseId(String courseId);
    List<EReviews> findByUserId(String userId);
    List<EReviews> findByCourseIdIn(List<String> courseIds);
    boolean existsByUserIdAndCourseId(String userId, String courseId);
}
