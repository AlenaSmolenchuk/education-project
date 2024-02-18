package ru.mts.educationproject.educationprojectstarter.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;

/**
 * Реализация BeanPostProcessor для инициализации типа животного в бинах типа CreateAnimalService.
 */
@Component
public class AnimalTypeInitializationBeanPostProcessor implements BeanPostProcessor {


    /**
     * Определяет тип животного перед инициализацией бина.
     *
     * @param bean     Экземпляр обрабатываемого бина.
     * @param beanName название бина.
     * @return проинициализированный бин.
     * @throws BeansException Если произошла ошибка во время обработки бина.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            ((CreateAnimalService) bean).initializeAnimalType();
        }
        return bean;
    }

    /**
     * Возвращает исходный бин после инициализации.
     *
     * @param bean     Экземпляр обрабатываемого бина.
     * @param beanName название бина.
     * @return Обработанный бин.
     * @throws BeansException Если произошла ошибка во время обработки бина.
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}