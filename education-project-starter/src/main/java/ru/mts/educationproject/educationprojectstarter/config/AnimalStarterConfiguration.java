package ru.mts.educationproject.educationprojectstarter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.factory.DogFactory;
import ru.mts.educationproject.educationprojectstarter.factory.SharkFactory;
import ru.mts.educationproject.educationprojectstarter.factory.WolfFactory;
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
    public CreateAnimalService createAnimalService(AnimalFactory wolfFactory,
                                                   AnimalFactory dogFactory,
                                                   AnimalFactory sharkFactory) {
        return new CreateAnimalServiceImpl((WolfFactory) wolfFactory,
                (DogFactory) dogFactory,
                (SharkFactory) sharkFactory);
    }

    /**
     * Создает и возвращает экземпляр фабрики для создания волков.
     *
     * @return экземпляр AnimalFactory
     */
    @Bean
    public WolfFactory wolfFactory() {
        return new WolfFactory();
    }

    /**
     * Создает и возвращает экземпляр фабрики для создания собак.
     *
     * @return экземпляр DogFactory
     */
    @Bean
    public DogFactory dogFactory() {
        return new DogFactory();
    }

    /**
     * Создает и возвращает экземпляр фабрики для создания акул.
     *
     * @return экземпляр SharkFactory
     */
    @Bean
    public SharkFactory sharkFactory() {
        return new SharkFactory();
    }
}