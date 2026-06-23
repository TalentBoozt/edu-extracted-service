package com.talentboozt.edu_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.data.mongodb.uri=mongodb://localhost:27017",
    "spring.data.mongodb.database=TalnovaEduTest"
})
class EduServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
