package ru.mts.service;

import org.springframework.context.annotation.Scope;
import ru.mts.factory.AnimalFactory;
import ru.mts.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService и AnimalFactory для создания животных.
 */
@Scope("prototype")
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private String animalType;

    public CreateAnimalServiceImpl() {
    }

    @Override
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    public Animal[] createAnimals(int n) {

        Animal[] uniqueAnimals = new Animal[n];
        int index = 0;

        do {
            uniqueAnimals[index++] = AnimalFactory.createRandomAnimal();
        } while (index < n);

        return uniqueAnimals;
    }
}
