package ru.mts.service;

import ru.mts.model.animalint.Animal;

import java.util.HashSet;
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
    default Set<Animal> createAnimalsWithWhile(int n) {
        Set<Animal> uniqueAnimals = new HashSet<>();

        while (uniqueAnimals.size() < n) {
            uniqueAnimals.add(createRandomAnimal());
        }
        return uniqueAnimals;
    }

    /**
     * Создает N уникальных животных при помощи цикла for.
     *
     * @return список созданных животных
     */
    Set<Animal> createAnimalsWithFor();


    /**
     * Метод для создания случайного животного.
     *
     * @return случайное животное
     */
    Animal createRandomAnimal();
}