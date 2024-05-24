package ru.mts.educationproject.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import ru.mts.educationproject.annotations.Logging;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.exception.UnknownAgeFormatException;
import ru.mts.educationproject.repository.dao.AnimalRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса AnimalsRepository для хранения и обработки информации о животных.
 */
@Log4j2
@Component
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private final ObjectMapper objectMapper;
    private final AnimalRepository animalRepository;

    /**
     * Конструктор класса, принимающий на вход сервис для создания животных.
     */
    public AnimalsRepositoryImpl(ObjectMapper objectMapper, AnimalRepository animalRepository) {
        this.objectMapper = objectMapper;
        this.animalRepository = animalRepository;
    }

    /**
     * Метод поиска имен животных, родившихся в високосные годы.
     *
     * @return Map, где ключ - тип животного + имя, значение - дата рождения
     */
    @Logging(value = "Find leap year names",
            enter = true,
            exit = true,
            logResult = true)
    @Override
    public Map<String, Integer> findLeapYearNames() {
        Map<String, Integer> leapYearNames = animalRepository.findAll().stream()
                .filter(animal -> LocalDate.now().minusYears(animal.getAge()).isLeapYear())
                .collect(Collectors.toConcurrentMap(
                        Animal::getName,
                        animal -> (int) animal.getAge(),
                        (existing, replacement) -> existing > replacement ? existing : replacement,
                        ConcurrentHashMap::new
                ));
        return leapYearNames;
    }

    /**
     * Метод поиска животных, старше заданного возраста.
     *
     * @param age заданный возраст для поиска
     * @return Map животных, старше заданного возраста или самое взрослое животное
     */
    @Logging(value = "Find older animals",
            enter = true,
            exit = true,
            logParams = true,
            logResult = true)
    @Override
    public Map<Animal, Integer> findOlderAnimals(short age) {
        if (age < 0 || age > 100) {
            log.error(new UnknownAgeFormatException("Unknown age format: " + age));
        }

        Map<Animal, Integer> olderAnimals = animalRepository.findByAgeGreaterThanEqual(age)
                .stream()
                .collect(Collectors.toConcurrentMap(
                        animal -> animal,
                        animal -> (int) animal.getAge(),
                        Integer::sum,
                        ConcurrentHashMap::new
                ));

        if (olderAnimals.isEmpty()) {
            log.info("No older animals found. The oldest animal is: ");
            Animal oldestAnimal = findOldest();
            int oldestAnimalAge = oldestAnimal.getAge();
            olderAnimals.put(oldestAnimal, oldestAnimalAge);
        }

        return olderAnimals;
    }

    /**
     * Метод поиска дубликатов животных в хранилище.
     *
     * @return множество дубликатов животных
     */
    @Logging(value = "Finding duplicate animals",
            enter = true,
            exit = true)
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        Map<String, List<Animal>> duplicates =
                animalRepository.findAll().stream()
                        .collect(Collectors.groupingBy(
                                animal -> animal.getType() + " " +
                                        animal.getName() + " " +
                                        animal.getBreed() + " " +
                                        animal.getAge(),
                                ConcurrentHashMap::new,
                                Collectors.toList()
                        ))
                        .entrySet().stream()
                        .filter(entry -> entry.getValue().size() > 1)
                        .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));


        return duplicates;
    }

    /**
     * Метод вывода дубликатов животных в консоль.
     * Если дубликаты отсутствуют, выводит соответствующее сообщение.
     */
    @Logging(value = "Duplicate animals found",
            enter = true,
            exit = true)
    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> duplicateAnimals = findDuplicate();
        if (!duplicateAnimals.isEmpty()) {
            duplicateAnimals.forEach((animalType, duplicates) -> {
                List<String> type = List.of(animalType.split(" "));
                System.out.println(type.get(0) + ": ");
                duplicates.forEach(System.out::println);
            });
        } else {
            log.info("No duplicate animals found.");
        }
    }

    /**
     * Метод нахождения среднего возраста животных.
     */
    @Logging(value = "Finding average age",
            enter = true,
            exit = true,
            logResult = true)
    @Override
    public double findAverageAge() {
        return animalRepository.findAll().stream()
                .mapToDouble(Animal::getAge)
                .average()
                .orElse(0);
    }

    // Вспомогательный метод для нахождения самого взрослого животного
    @Logging(value = "Find oldest",
            enter = true,
            exit = true)
    private Animal findOldest() {
        return animalRepository.findAll().stream()
                .max(Comparator.comparingInt(Animal::getAge))
                .orElse(null);
    }
}