package com.talentboozt.edu_service.domains.edu.repository.mongodb;

import com.talentboozt.edu_service.domains.edu.model.ECreatorFinanceSettings;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ECreatorFinanceSettingsRepository extends MongoRepository<ECreatorFinanceSettings, String> {
    Optional<ECreatorFinanceSettings> findByUserId(String userId);
}
