package ru.mts.educationproject.repository;

import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.exception.AnimalsArrayException;
import ru.mts.educationproject.exception.UnknownAgeFormatException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ru.mts.educationproject.util.Helper.calculateAge;
import static ru.mts.educationproject.util.Helper.print;

/**
 * Реализация интерфейса AnimalsRepository для хранения и обработки информации о животных.
 */
@Component
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalService createAnimalService;
    private Map<String, List<Animal>> animals;

    /**
     * Конструктор класса, принимающий на вход сервис для создания животных.
     *
     * @param createAnimalService сервис для создания животных
     */
    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    /**
     * Метод инициализации животных при старте приложения.
     * Создает 20 животных при помощи сервиса для создания животных.
     * Выводит информацию о созданных животных в консоль.
     */
    public void initAnimals() {

        System.out.println("Creating animals:");

        animals = createAnimalService.createAnimals(20);

        print(animals);
    }

    /**
     * Метод поиска имен животных, родившихся в високосные годы.
     *
     * @return Map, где ключ - тип животного + имя, значение - дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> isLeapYear(animal.getDateOfBirth().getYear()))
                .collect(Collectors.toConcurrentMap(
                        animal -> animal.getType() + " " + animal.getName(),
                        Animal::getDateOfBirth,
                        (existing, replacement) -> existing.isAfter(replacement) ? existing : replacement,
                        ConcurrentHashMap::new
                ));
    }

    /**
     * Метод поиска животных, старше заданного возраста.
     *
     * @param age заданный возраст для поиска
     * @return Map животных, старше заданного возраста или самое взрослое животное
     */
    @Override
    public Map<Animal, Integer> findOlderAnimals(int age) {
        if (age < 0 || age > 100) {
            throw new UnknownAgeFormatException("Unknown age format: " + age);
        }

        Map<Animal, Integer> olderAnimals = animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> calculateAge(animal.getDateOfBirth()) > age)
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> calculateAge(animal.getDateOfBirth()),
                        Integer::sum,
                        ConcurrentHashMap::new
                ));

        if (olderAnimals.isEmpty()) {
            System.out.println("No older animals found. The oldest Animal is: ");
            Animal oldestAnimal = findOldest(
                    animals.values().stream()
                            .flatMap(List::stream)
                            .collect(Collectors.toCollection(CopyOnWriteArrayList::new))
            );

            int oldestAnimalAge = calculateAge(oldestAnimal.getDateOfBirth());
            olderAnimals.put(oldestAnimal, oldestAnimalAge);
        }
        return olderAnimals;
    }

    /**
     * Метод поиска дубликатов животных в хранилище.
     *
     * @return множество дубликатов животных
     */
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        return animals.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        animal -> animal.getType() + " " +
                                animal.getName() + " " +
                                animal.getBreed() + " " +
                                animal.getCharacter() + " " +
                                animal.getDateOfBirth() + " " +
                                animal.getCost(),
                        ConcurrentHashMap::new,
                        Collectors.toCollection(CopyOnWriteArrayList::new)
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    /**
     * Метод вывода дубликатов животных в консоль.
     * Если дубликаты отсутствуют, выводит соответствующее сообщение.
     */
    @Override
    public void printDuplicate() {
        System.out.println("Finding duplicate animals: ");
        Map<String, List<Animal>> duplicateAnimals = findDuplicate();
        if (!duplicateAnimals.isEmpty()) {
            System.out.println("Duplicate animals found:");
            duplicateAnimals.forEach((animalType, duplicates) -> {
                List<String> type = List.of(animalType.split(" "));
                System.out.println(type.get(0) + ": ");
                duplicates.forEach(System.out::println);
            });
        } else {
            System.out.println("No duplicate animals found.");
        }
    }

    /**
     * Метод нахождения среднего возраста животных.
     */
    @Override
    public void findAverageAge() {
        System.out.println("Finding animals' average age: ");
        double averageAge = animals.values().stream()
                .flatMap(List::stream)
                .mapToDouble(animal -> calculateAge(animal.getDateOfBirth()))
                .average()
                .orElse(0);
        System.out.println("Average age of animals: " + averageAge);
    }

    /**
     * Метод нахождения списка животных, возраст которых больше 5 лет и стоимость которых
     * превышает среднюю стоимость всех животных. Результат отсортирован по дате рождения
     * в порядке возрастания.
     *
     * @return Список животных, соответствующих условиям по возрасту и стоимости.
     */
    @Override
    public List<Animal> findOldAndExpensive() {
        BigDecimal averageCost = calculateAverageCost(animals);

        return animals.values().stream()
                .flatMap(List::stream)
                .filter(animal -> calculateAge(animal.getDateOfBirth()) > 5
                        && animal.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getDateOfBirth))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    /**
     * Метод нахождения списка имен животных с минимальной стоимостью.
     * Результат отсортирован в обратном алфавитном порядке.
     *
     * @return Список имен животных с минимальной стоимостью.
     */
    @Override
    public List<String> findMinCostAnimals() throws AnimalsArrayException {
        if (animals == null || animals.size() < 3) {
            throw new AnimalsArrayException("The 'animals' map is null or contains less than 3 elements.");
        }

        return animals.values().stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    public void setAnimals(Map<String, List<Animal>> animals) {
        this.animals = animals;
    }

    // Вспомогательный метод для определения високосного года
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Вспомогательный метод для нахождения самого взрослого животного
    private Animal findOldest(List<Animal> animalList) {
        return animalList.stream()
                .max(Comparator.comparingInt(animal -> calculateAge(animal.getDateOfBirth())))
                .orElseThrow();
    }
}