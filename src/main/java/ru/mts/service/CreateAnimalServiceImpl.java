package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.factory.DogFactory;
import ru.mts.factory.SharkFactory;
import ru.mts.factory.WolfFactory;
import ru.mts.model.animalint.Animal;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService, AnimalFactory {

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    @Override
    public Animal[] createAnimals(int n) {
        Animal[] uniqueAnimals = new Animal[n];
        Set<Animal> uniqueAnimalsSet = new HashSet<>();

        int index = 0;
        do {
            Animal randomAnimal = createRandomAnimal();
            if (containsAnimal(uniqueAnimalsSet, randomAnimal)) {
                uniqueAnimalsSet.add(randomAnimal);
                uniqueAnimals[index++] = randomAnimal;
            }
        } while (index < n);

        return uniqueAnimals;
    }

    // Перегруженный метод createAnimals с использованием цикла for
    public Animal[] createAnimals() {
        Animal[] uniqueAnimals = new Animal[10];
        Set<Animal> uniqueAnimalsSet = new HashSet<>();

        for (int i = 0, index = 0; i < 10; i++) {
            Animal randomAnimal = createRandomAnimal();
            if (containsAnimal(uniqueAnimalsSet, randomAnimal)) {
                uniqueAnimalsSet.add(randomAnimal);
                uniqueAnimals[index++] = randomAnimal;
            }
        }

        return uniqueAnimals;
    }

    // Реализация метода createRandomAnimal, создающего случайное животное
    @Override
    public Animal createRandomAnimal() {
        String[] breeds = {"Wolf", "Shark", "Dog"};

        String breed = breeds[(int) (Math.random() * breeds.length)];
        return getAnimalFactory(breed).createRandomAnimal();
    }

    // Перегруженный метод containsAnimal
    private boolean containsAnimal(Set<Animal> animals, Animal animal) {
        return !animals.contains(animal);

    }

    private AnimalFactory getAnimalFactory(String breed) {
        return switch (breed) {
            case "Wolf" -> new WolfFactory();
            case "Shark" -> new SharkFactory();
            case "Dog" -> new DogFactory();
            default -> throw new IllegalArgumentException("Unknown breed: " + breed);
        };
}
