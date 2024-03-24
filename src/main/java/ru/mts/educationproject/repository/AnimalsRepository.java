package ru.mts.educationproject.repository;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.exception.AnimalsArrayException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.time.LocalDate;

/**
 * Объявление интерфейса, представляющий репозиторий для работы с животными.
 */
public interface AnimalsRepository {

    /**
     * Возвращает Map, где ключ - тип животного + имя, а значение - дата рождения животного.
     *
     * @return Map с ключами в виде типа животного + имя и значениями в виде даты рождения.
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Возвращает Map, где ключ - животное, а значение - возраст животного.
     *
     * @param age возраст, по отношению к которому производится поиск.
     * @return Map с ключами в виде животных и значениями в возраста.
     */
    Map<Animal, Integer> findOlderAnimals(int age);

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
     * Устанавливает коллекцию животных в репозитории.
     *
     * @param animals Коллекция, где ключ - тип животного, а значение - список животных этого типа.
     */
    void setAnimals(Map<String, List<Animal>> animals);

    /**
     * Метод для получения сохраненного среднего возраста.
     *
     * @return средний возраст животных.
     */
    double getAverageAge();

    /**
     * Метод инициализации животных при старте приложения.
     */
    void initAnimals();

    /**
     * Метод для вычисления средней стоимости животных.
     *
     * @param animals Коллекция, где ключ - тип животного, а значение - список животных этого типа.
     * @return средняя стоимость животных.
     */
    default BigDecimal calculateAverageCost(Map<String, List<Animal>> animals) {
        return animals.values().stream()
                .flatMap(List::stream)
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(
                                animals.values().stream()
                                        .mapToInt(List::size)
                                        .sum()),
                        RoundingMode.HALF_UP);
    }

    /**
     * Метод нахождения среднего возраста животных.
     */
    void findAverageAge();

    /**
     * Метод нахождения списка животных, возраст которых больше 5 лет и стоимость которых
     * превышает среднюю стоимость всех животных. Результат отсортирован по дате рождения
     * в порядке возрастания.
     *
     * @return Список животных, соответствующих условиям по возрасту и стоимости.
     */
    List<Animal> findOldAndExpensive();

    /**
     * Метод нахождения списка имен животных с минимальной стоимостью.
     * Результат отсортирован в обратном алфавитном порядке.
     *
     * @return Список имен животных с минимальной стоимостью.
     */
    List<String> findMinCostAnimals() throws AnimalsArrayException;

}