package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.mts.service.CreateAnimalService;

/**
 * Реализация BeanPostProcessor для инициализации типа животного в бинах типа CreateAnimalService.
 */
@Component
public class AnimalTypeInitializationBeanPostProcessor implements BeanPostProcessor {


    /**
     * Инициализирует тип животного перед инициализацией бина.
     *
     * @param bean     Экземпляр обрабатываемого бина.
     * @param beanName Имя бина.
     * @return Обработанный бин.
     * @throws BeansException Если произошла ошибка во время обработки бина.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            initializeAnimalType((CreateAnimalService) bean);
        }
        return bean;
    }

    // Инициализирует тип животного для указанного экземпляра CreateAnimalService
    private void initializeAnimalType(CreateAnimalService createAnimalService) {
        String[] availableTypes = {"Wolf", "Shark", "Dog"};
        String randomType = availableTypes[(int) (Math.random() * availableTypes.length)];

        createAnimalService.setAnimalType(randomType);
    }


    /**
     * Возвращает исходный бин после инициализации.
     *
     * @param bean     Экземпляр обрабатываемого бина.
     * @param beanName Имя бина.
     * @return Обработанный бин.
     * @throws BeansException Если произошла ошибка во время обработки бина.
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}