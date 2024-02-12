package ru.mts.educationproject.educationprojectstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalServiceImpl;



/**
 * Конфигурационный класс Spring приложения для определения бинов.
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class AnimalStarterConfiguration {

    /**
     * Создает и возвращает экземпляр сервиса для создания животных.
     *
     * @return экземпляр CreateAnimalService
     */
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}