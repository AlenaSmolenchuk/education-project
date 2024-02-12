package ru.mts.educationproject.educationprojectstarter.service;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

/**
 * Объявление интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     * @return массив созданных животных
     */
    Animal[] createAnimals(int n);

    /**
     * Устанавливает тип животного для последующего создания.
     *
     * @param animalType тип животного, который будет использоваться при создании
     */
    void setAnimalType(String animalType);

}