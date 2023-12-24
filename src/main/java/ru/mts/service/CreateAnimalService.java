package ru.mts.service;

import ru.mts.factory.AnimalFactory;
import ru.mts.model.animalint.Animal;

/**
 * Реализация интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает N уникальных животных при помощи цикла while.
     *
     * @param n количество животных для создания
     * @return массив созданных животных
     */
    default Animal[] createAnimalsWithWhile(int n, AnimalFactory animalFactory) {
        Animal[] uniqueAnimals = new Animal[n];
        int index = 0;

        while (index < n) {
            Animal randomAnimal = createRandomAnimal(animalFactory);
            if (!containsAnimal(uniqueAnimals, randomAnimal)) {
                uniqueAnimals[index++] = randomAnimal;
            }
        }

        return uniqueAnimals;
    }

    /**
     * Создает N уникальных животных при помощи цикла for.
     *
     * @return массив созданных животных
     */
    Animal[] createAnimalsWithFor();

    /**
     * Метод для создания случайного животного.
     *
     * @return случайное животное
     */
    default Animal createRandomAnimal(AnimalFactory animalFactory) {
        return animalFactory.createRandomAnimal();
    }

    /**
     * Проверяет, содержится ли животное в массиве.
     *
     * @param animals массив животных
     * @param animal  проверяемое животное
     * @return true, если животное содержится в массиве, иначе false
     */
    default boolean containsAnimal(Animal[] animals, Animal animal) {
        for (Animal a : animals) {
            if (a != null && a.equals(animal)) {
                return true;
            }
        }
        return false;
    }
}