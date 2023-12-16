package ru.mts.service;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Dog;
import ru.mts.model.animals.Shark;
import ru.mts.model.animals.Wolf;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    // Реализация метода createNAnimals с использованием цикла for
    @Override
    public void createAnimalsWithFor(int N) {
        Set<Animal> uniqueAnimals = new HashSet<>();
        for (int i = 0; i < N; i++) {
            Animal animal = createRandomAnimal();
            if (uniqueAnimals.add(animal)) {
                System.out.println("Created: " + animal);
            }
        }
    }

    // Реализация метода createRandomAnimal, создающего случайное животное
    @Override
    public Animal createRandomAnimal() {

        // В данном методе создается случайное животное типа Wolf, Shark или Dog
        String[] breeds = {"Wolf", "Shark", "Dog"};

        String breed = breeds[(int) (Math.random() * breeds.length)];
        String name = "Random" + System.currentTimeMillis();
        BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
        AnimalCharacter randomCharacter = AnimalCharacter.values()
                [(int) (Math.random() * AnimalCharacter.values().length)];

        if (breed.equals("Shark")) {
            return new Shark(name, cost, randomCharacter);
        } else if (breed.equals("Dog")) {
            return new Dog(name, cost, randomCharacter);
        } else {
            return new Wolf(name, cost, randomCharacter);
        }
    }

    /**
     * Создает N уникальных животных при помощи цикла do-while.
     */
    @Override
    public void createAnimalsWithWhile(int N) {
        N = 10;
        Set<Animal> uniqueAnimals = new HashSet<>();
        do {
            Animal animal = createRandomAnimal();
            if (uniqueAnimals.add(animal)) {
                System.out.println("Created: " + animal);
            }
        } while (uniqueAnimals.size() < N);
    }
}