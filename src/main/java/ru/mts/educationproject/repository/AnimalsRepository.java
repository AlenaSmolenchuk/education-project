package ru.mts.educationproject.repository;

import ru.mts.educationproject.entity.Animal;

import java.util.*;

/**
 * Объявление интерфейса, представляющий репозиторий для работы с животными.
 */
public interface AnimalsRepository {

    /**
     * Возвращает Map, где ключ - тип животного + имя, а значение - дата рождения животного.
     *
     * @return Map с ключами в виде типа животного + имя и значениями в виде даты рождения.
     */
    Map<String, Integer> findLeapYearNames();

    /**
     * Возвращает Map, где ключ - животное, а значение - возраст животного.
     *
     * @param age возраст, по отношению к которому производится поиск.
     * @return Map с ключами в виде животных и значениями в возраста.
     */
    Map<Animal, Integer> findOlderAnimals(short age);

    /**
     * Находит и возвращает множество дубликатов животных в репозитории.
     *
     * @return множество дубликатов животных.
     */
    Map<String, List<Animal>> findDuplicate();

    /**
     * Выводит в консоль информацию о дубликатах животных в репозитории.
     */
    void printDuplicate();

    /**
     * Метод нахождения среднего возраста животных.
     */
    double findAverageAge();
}