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

    private final AnimalFactory wolfFactory = new WolfFactory();
    private final AnimalFactory sharkFactory = new SharkFactory();
    private final AnimalFactory dogFactory = new DogFactory();

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    public Animal[] createAnimalsWithWhile(int n) {
        Animal[] uniqueAnimals = new Animal[n];
        Set<Animal> uniqueAnimalsSet = new HashSet<>();

        int index = 0;
        do {
            Animal randomAnimal = createRandomAnimal();
            if (containsAnimalOrNot(uniqueAnimalsSet, randomAnimal)) {
                uniqueAnimalsSet.add(randomAnimal);
                uniqueAnimals[index++] = randomAnimal;
            }
        } while (index < n);

        return uniqueAnimals;
    }

    // Реализация метода createNAnimals с использованием цикла for
    @Override
    public Animal[] createAnimalsWithFor() {
        Animal[] uniqueAnimals = new Animal[10];
        Set<Animal> uniqueAnimalsSet = new HashSet<>();

        for (int i = 0, index = 0; i < 10; i++) {
            Animal randomAnimal = createRandomAnimal();
            if (containsAnimalOrNot(uniqueAnimalsSet, randomAnimal)) {
                uniqueAnimalsSet.add(randomAnimal);
                uniqueAnimals[index++] = randomAnimal;
            }
        }

        return uniqueAnimals;
    }


    // Перегруженный метод для создания n животных с использованием цикла for
    public Animal[] createAnimalsWithFor(int n) {
        Animal[] uniqueAnimals = new Animal[n];
        Set<Animal> uniqueAnimalsSet = new HashSet<>();

        for (int i = 0, index = 0; i < n; i++) {
            Animal randomAnimal = createRandomAnimal();
            if (containsAnimalOrNot(uniqueAnimalsSet, randomAnimal)) {
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

    private boolean containsAnimalOrNot(Set<Animal> animals, Animal animal) {
        return !animals.contains(animal);

    }

    private AnimalFactory getAnimalFactory(String breed) {
        switch (breed) {
            case "Wolf":
                return wolfFactory;
            case "Shark":
                return sharkFactory;
            case "Dog":
                return dogFactory;
            default:
                throw new IllegalArgumentException("Unknown breed: " + breed);
        }
    }
}