package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.factory.AnimalFactory;
import ru.mts.bpp.AnimalTypeInitializationBeanPostProcessor;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public AnimalFactory animalFactory() {
        return new CreateAnimalServiceImpl.AnimalFactoryImpl();
    }

    @Bean
    public CreateAnimalService createAnimalService(AnimalFactory animalFactory) {
        return new CreateAnimalServiceImpl(animalFactory);
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }

    @Bean
    public AnimalTypeInitializationBeanPostProcessor animalTypeInitializationBeanPostProcessor() {
        return new AnimalTypeInitializationBeanPostProcessor();
    }
}
