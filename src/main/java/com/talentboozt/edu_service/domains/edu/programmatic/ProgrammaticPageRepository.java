package com.talentboozt.edu_service.domains.edu.programmatic;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.talentboozt.edu_service.domains.edu.model.EProgrammaticPage;
import java.util.Optional;

/**
 * Programmatic Page MongoDB Repository interface.
 */
@Repository
public interface ProgrammaticPageRepository extends MongoRepository<EProgrammaticPage, String> {
    
    /**
     * Resolves a programmatic landing page by its unique slug.
     */
    Optional<EProgrammaticPage> findBySlug(String slug);
}
