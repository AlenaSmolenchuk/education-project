package ru.mts.educationproject.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.testrepo.TestAnimalsRepositoryImpl;

@TestConfiguration
@Profile("test")
@EnableConfigurationProperties(TestAnimalProperties.class)
public class TestConfig {

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService,
                                               TestAnimalProperties testAnimalProperties) {

        return new TestAnimalsRepositoryImpl(createAnimalService, testAnimalProperties);
    }
}