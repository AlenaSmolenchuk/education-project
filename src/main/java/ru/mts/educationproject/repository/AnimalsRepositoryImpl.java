package ru.mts.educationproject.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.exception.FileException;
import ru.mts.educationproject.exception.UnknownAgeFormatException;
import ru.mts.educationproject.repository.dao.AnimalRepository;
import ru.mts.educationproject.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static ru.mts.educationproject.util.Helper.findOldest;

/**
 * Реализация интерфейса AnimalsRepository для хранения и обработки информации о животных.
 */
@Component
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private static final Logger log = LoggerFactory.getLogger(AnimalsRepositoryImpl.class);
    private final ObjectMapper objectMapper;
    private final AnimalRepository animalRepository;

    /**
     * Конструктор класса, принимающий на вход сервис для создания животных.
     *
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
    @Override
    public Map<String, LocalDate> findLeapYearNames() {

        Map<String, LocalDate> leapYearNames = animalRepository.findAll().stream()
                .filter(animal -> LocalDate.now().minusYears(animal.getAge()).isLeapYear())
                .collect(Collectors.toConcurrentMap(
                        Animal::getName,
                        animal -> LocalDate.now().minusYears(animal.getAge()),
                        (existing, replacement) -> existing.isAfter(replacement) ? existing : replacement,
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
    @Override
    public Map<Animal, Integer> findOlderAnimals(short age) {
        if (age < 0 || age > 100) {
            throw new UnknownAgeFormatException("Unknown age format: " + age);
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
            Animal oldestAnimal = findOldest(animalRepository.findAll());
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
    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> duplicateAnimals = findDuplicate();
        if (!duplicateAnimals.isEmpty()) {
            log.info("Duplicate animals found:");
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
    @Override
    public double findAverageAge() {
        log.info("Finding animals' average age: ");
        double averageAge = animalRepository.findAll().stream()
                .mapToDouble(Animal::getAge)
                .average()
                .orElse(0);

        return averageAge;
    }

    private void writeJson(Object data, String fileName) {
        try {
            Path filePath = ResourceUtils.getFile(fileName).toPath();
            Files.createDirectories(filePath.getParent());
            objectMapper.writeValue(filePath.toFile(), data);
        } catch (IOException e) {
            log.error("Failed to write data to JSON file: {}", e.getMessage(), e);
        }
    }

    public <T> T readJson(String fileName, TypeReference<T> typeReference) throws IOException {
        try {
            Path filePath = ResourceUtils.getFile(fileName).toPath();
            if (!Files.exists(filePath)) {
                log.error("File {} not found", fileName);
                throw new FileException("File not found: " + fileName);
            }
            return objectMapper.readValue(filePath.toFile(), typeReference);
        } catch (IOException e) {
            log.error("Failed to read data from JSON file: {} {}",
                    fileName,
                    e.getMessage(),
                    e);
            throw e;
        }
    }
}