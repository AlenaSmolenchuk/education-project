package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.factory.DogFactory;
import ru.mts.factory.SharkFactory;
import ru.mts.factory.WolfFactory;
import ru.mts.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService и AnimalFactory для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService, AnimalFactory {

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

            uniqueAnimals[index++] = createRandomAnimal();

        } while (index < n);

        return uniqueAnimals;
    }

    // Реализация метода createRandomAnimal, создающего случайное животное
    @Override
    public Animal createRandomAnimal() {
        String[] breeds = {"Wolf", "Shark", "Dog"};

        String breed = breeds[(int) (Math.random() * breeds.length)];
        return getAnimalFactory(breed).createRandomAnimal();
    }

    // Возвращает соответствующую фабрику для создания объектов указанного вида животных.
    private AnimalFactory getAnimalFactory(String breed) {
        switch (breed) {
            case "Wolf":
                return new WolfFactory();
            case "Shark":
                return new SharkFactory();
            case "Dog":
                return new DogFactory();
            default:
                throw new IllegalArgumentException("Unknown breed: " + breed);
        }
    }
}