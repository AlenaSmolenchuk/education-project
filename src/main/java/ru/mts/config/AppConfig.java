package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.repository.AnimalsRepository;
import ru.mts.repository.AnimalsRepositoryImpl;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

/**
 * Конфигурационный класс Spring приложения для определения бинов.
 */
@Configuration
public class AppConfig {

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

    /**
     * Создает и возвращает экземпляр репозитория животных с указанным сервисом создания животных.
     *
     * @param createAnimalService сервис для создания животных
     * @return экземпляр AnimalsRepository
     */
    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
