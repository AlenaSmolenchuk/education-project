package ru.mts.service;

import ru.mts.model.animalint.Animal;

/**
 * Объявление интерфейса CreateAnimalService предоставляет методы для поиска животных по различным критериям.
 */
public interface SearchService {

    /**
     * Найти имена животных, родившихся в високосный год.
     *
     * @param animals массив животных
     * @return массив имен животных, родившихся в високосный год
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * Найти животных, чей возраст старше указанного числа лет.
     *
     * @param animals массив животных
     * @param age     возраст, сравниваемый с возрастом животных
     * @return массив животных, чей возраст старше указанного числа лет
     */
    Animal[] findOlderAnimal(Animal[] animals, int age);

    /**
     * Вывести на экран дубликаты животных в массиве.
     *
     * @param animals массив животных
     */
    void findDuplicate(Animal[] animals);

}
