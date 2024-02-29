package ru.mts.educationproject.educationprojectstarter.service;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.util.*;

/**
 * Объявление интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     * @return массив созданных животных
     */
    Map<String, List<Animal>> createAnimals(int n);

    /**
     * Определяет тип животного для последующего создания.
     *
     * @return тип животного
     */
    String initializeAnimalType();

    /**
     * Создает животное в соотвествии с типом.
     *
     * @return животное определенного типа
     */
    Animal createRandomAnimalByType(String animalType);

}