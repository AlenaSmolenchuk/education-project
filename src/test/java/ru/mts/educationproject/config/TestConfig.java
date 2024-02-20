package ru.mts.educationproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.mts.educationproject.educationprojectstarter.factory.DogFactory;
import ru.mts.educationproject.educationprojectstarter.factory.SharkFactory;
import ru.mts.educationproject.educationprojectstarter.factory.WolfFactory;

@TestConfiguration
@Profile("test")
public class TestConfig {

    @Value("${animal.wolf.names}")
    private String[] wolfNames;

    @Value("${animal.dog.names}")
    private String[] dogNames;

    @Value("${animal.shark.names}")
    private String[] sharkNames;

    @Bean
    public WolfFactory testWolfFactory() {
        return new WolfFactory(wolfNames);
    }

    @Bean
    public DogFactory testDogFactory() {
        return new DogFactory(dogNames);
    }

    @Bean
    public SharkFactory testSharkFactory() {
        return new SharkFactory(sharkNames);
    }
}
