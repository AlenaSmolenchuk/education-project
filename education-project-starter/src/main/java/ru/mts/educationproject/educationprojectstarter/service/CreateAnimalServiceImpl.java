package ru.mts.educationproject.educationprojectstarter.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final AnimalFactory wolfFactory;
    private final AnimalFactory dogFactory;
    private final AnimalFactory sharkFactory;

    private String animalType;

    public CreateAnimalServiceImpl(AnimalFactory wolfFactory,
                                   AnimalFactory dogFactory,
                                   AnimalFactory sharkFactory) {
        this.wolfFactory = wolfFactory;
        this.dogFactory = dogFactory;
        this.sharkFactory = sharkFactory;
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