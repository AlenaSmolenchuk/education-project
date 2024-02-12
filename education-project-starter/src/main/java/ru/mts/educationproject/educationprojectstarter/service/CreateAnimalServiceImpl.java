package ru.mts.educationproject.educationprojectstarter.service;

import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.util.List;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final List<AnimalFactory> factories;
    private String animalType;

    /**
     * Конструктор сервиса для создания животных.
     *
     * @param wolfFactory Фабрика для создания волков.
     * @param dogFactory Фабрика для создания собак.
     * @param sharkFactory Фабрика для создания акул.
     */
    public CreateAnimalServiceImpl(AnimalFactory wolfFactory,
                                   AnimalFactory dogFactory,
                                   AnimalFactory sharkFactory) {
        this.factories = List.of(wolfFactory,dogFactory,sharkFactory);
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
            uniqueAnimals[index++] = factories.get(index % factories.size()).createRandomAnimal();
        } while (index < n);

        return uniqueAnimals;
    }

    public String getAnimalType() {
        return animalType;
    }

    //Устанавливает тип животного
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}