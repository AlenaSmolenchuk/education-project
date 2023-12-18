package ru.mts.service;

import ru.mts.model.animalint.Animal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Реализация интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает N уникальных животных при помощи цикла while.
     * @param n количество животных для создания
     *
     * @return список созданных животных
     */
    default List<Animal> createAnimalsWithWhile(int n) {
        Set<Animal> uniqueAnimals = new HashSet<>();
        List<Animal> createdAnimals = new ArrayList<>();

        while (uniqueAnimals.size() < n) {
            Animal animal = createRandomAnimal();
            if (uniqueAnimals.add(animal)) {
                createdAnimals.add(animal);
            }
        }
        return createdAnimals;
    }

    /**
     * Создает N уникальных животных при помощи цикла for.
     *
     * @return список созданных животных
     */
    List<Animal> createAnimalsWithFor();


    /**
     * Метод для создания случайного животного.
     *
     * @return случайное животное
     */
    Animal createRandomAnimal();
}