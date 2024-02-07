package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private String animalType;

    /**
     * Конструктор без параметров.
     */
    public CreateAnimalServiceImpl() {
    }

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    @Override
    public Animal[] createAnimals(int n) {

        Animal[] uniqueAnimals = new Animal[n];
        int index = 0;

        do {
            uniqueAnimals[index++] = AnimalFactory.createRandomAnimal();
        } while (index < n);

        return uniqueAnimals;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
