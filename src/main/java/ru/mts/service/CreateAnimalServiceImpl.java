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

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    @Override
    public Set<Animal> createAnimalsWithWhile(int n) {
        Set<Animal> uniqueAnimals = new HashSet<>();

        do {
            uniqueAnimals.add(createRandomAnimal());

        } while (uniqueAnimals.size() < n);

        return uniqueAnimals;
    }

    // Реализация метода createNAnimals с использованием цикла for
    @Override
    public Set<Animal> createAnimalsWithFor() {
        Set<Animal> uniqueAnimals = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            uniqueAnimals.add(createRandomAnimal());
        }
        return uniqueAnimals;
    }

    // Перегруженный метод для создания n животных с использованием цикла for
    public Set<Animal> createAnimalsWithFor(int n) {
        Set<Animal> uniqueAnimals = new HashSet<>();

        for (int i = 0; i < n; i++) {
            Animal animal = createRandomAnimal();
            uniqueAnimals.add(createRandomAnimal());
        }

        return uniqueAnimals;
    }

    // Реализация метода createRandomAnimal, создающего случайное животное
    @Override
    public Animal createRandomAnimal() {
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
}