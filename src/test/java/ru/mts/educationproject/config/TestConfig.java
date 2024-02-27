package ru.mts.educationproject.config;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.mts.educationproject.educationprojectstarter.factory.DogFactory;
import ru.mts.educationproject.educationprojectstarter.factory.SharkFactory;
import ru.mts.educationproject.educationprojectstarter.factory.WolfFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animals.Dog;
import ru.mts.educationproject.educationprojectstarter.model.animals.Shark;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Bean
    public CreateAnimalService testCreateAnimalServiceMock() {
        return Mockito.mock(CreateAnimalService.class);
    }
}