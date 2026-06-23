package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.talentboozt.edu_service.domains.edu.model.EQuizzes;

import java.util.Optional;

@Repository
public interface EQuizzesRepository extends MongoRepository<EQuizzes, String> {
    Optional<EQuizzes> findByLessonId(String lessonId);
}
