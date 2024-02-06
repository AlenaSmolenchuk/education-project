package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.mts.factory.AnimalFactory;
import ru.mts.service.CreateAnimalService;

@Component
public class AnimalTypeInitializationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private AnimalFactory animalFactory;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalService) {
            initializeAnimalType((CreateAnimalService) bean);
        }
        return bean;
    }

    private void initializeAnimalType(CreateAnimalService createAnimalService) {
        String[] availableTypes = {"Wolf", "Shark", "Dog"};
        String randomType = availableTypes[(int) (Math.random() * availableTypes.length)];

        createAnimalService.setAnimalType(randomType);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}