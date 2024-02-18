package ru.mts.educationproject.educationprojectstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalServiceImpl;


/**
 * Конфигурационный класс Spring приложения для определения бинов.
 */
@Configuration
public class AnimalStarterConfiguration {

    /**
     * Создает и возвращает экземпляр сервиса для создания животных.
     *
     * @return экземпляр CreateAnimalService
     */
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService(AnimalFactory wolfFactory,
                                                   AnimalFactory dogFactory,
                                                   AnimalFactory sharkFactory) {
        return new CreateAnimalServiceImpl(wolfFactory, dogFactory, sharkFactory);
    }
}