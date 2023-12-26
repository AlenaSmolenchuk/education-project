package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     * @return массив созданных животных
     */
    Animal[] createAnimals(int n);

    /**
     * Метод для создания случайного животного.
     *
     * @return случайное животное
     */
    default Animal createRandomAnimal(AnimalFactory animalFactory) {
        return animalFactory.createRandomAnimal();
    }

}