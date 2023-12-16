package ru.mts.service;

import ru.mts.model.animalint.Animal;

import java.util.HashSet;
import java.util.Set;

/**
 * Интерфейс CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает N уникальных животных при помощи цикла while.
     */
    default void createAnimalsWithWhile(int N) {
        N = 10;
        Set<Animal> uniqueAnimals = new HashSet<>();
        while (uniqueAnimals.size() < N) {
            Animal animal = createRandomAnimal();
            if (uniqueAnimals.add(animal)) {
                System.out.println("Created: " + animal);
            }
        }
    }

    /**
     * Создает N уникальных животных при помощи цикла for.
     *
     * @param N количество животных для создания
     */
    void createAnimalsWithFor(int N);


    /**
     * Метод для создания случайного животного.
     *
     * @return случайное животное
     */
    Animal createRandomAnimal();
}