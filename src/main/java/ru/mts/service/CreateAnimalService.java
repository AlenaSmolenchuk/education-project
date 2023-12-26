package ru.mts.service;

import ru.mts.model.animalint.Animal;

/**
 *  Объявление интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     * @return массив созданных животных
     */
    Animal[] createAnimals(int n);

}