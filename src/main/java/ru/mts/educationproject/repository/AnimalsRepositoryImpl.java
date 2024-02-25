package ru.mts.educationproject.repository;

import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.scheduler.AnimalScheduler;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;

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
     * Создает 5 животных при помощи сервиса для создания животных.
     * Выводит информацию о созданных животных в консоль.
     */
    @PostConstruct
    public void initAnimals() {

        System.out.println("Creating animals:");

        animals = createAnimalService.createAnimals(10);

        AnimalScheduler.print(animals);
    }

    /**
     * Метод поиска имен животных, родившихся в високосные годы.
     *
     * @return Map, где ключ - тип животного + имя, значение - дата рождения
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> leapYearNames = new HashMap<>();
        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                if (isLeapYear(animal.getDateOfBirth().getYear())) {
                    String key = animal.getType() + " " + animal.getName();
                    leapYearNames.put(key, animal.getDateOfBirth());
                }
            }
        }
        return leapYearNames;
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
            throw new IllegalArgumentException("Unknown age format: " + age);
        }

        Map<Animal, Integer> olderAnimals = new HashMap<>();
        int maxAge = -1;

        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                int years = LocalDate.now().getYear() - animal.getDateOfBirth().getYear();
                if (years > age) {
                    olderAnimals.put(animal, years);
                    maxAge = Math.max(maxAge, years);
                }
            }
        }

        if (olderAnimals.isEmpty() && maxAge != -1) {
            Animal oldestAnimal = findOldest();
            olderAnimals.put(oldestAnimal, maxAge);
        }

        return olderAnimals;
    }

    /**
     * Метод поиска дубликатов животных в хранилище.
     *
     * @return множество дубликатов животных
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Set<Animal>> uniqueAnimals = new HashMap<>();
        Map<String, Integer> duplicateAnimals = new HashMap<>();

        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                String animalType = animal.getType();
                Set<Animal> animalSet = uniqueAnimals.computeIfAbsent(animalType, k -> new HashSet<>());
                if (!animalSet.add(animal)) {
                    duplicateAnimals.put(animalType,
                            duplicateAnimals.getOrDefault(animalType, 1  ) + 1);
                }
            }
        }

        return duplicateAnimals;
    }

    /**
     * Метод вывода дубликатов животных в консоль.
     * Если дубликаты отсутствуют, выводит соответствующее сообщение.
     */
    @Override
    public void printDuplicate() {
        Map<String, Integer> duplicateAnimals = findDuplicate();
        if (!duplicateAnimals.isEmpty()) {
            System.out.println("Duplicate animals found:");
            for (Map.Entry<String, Integer> entry : duplicateAnimals.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        } else {
            System.out.println("No duplicate animals found.");
        }
    }

    // Вспомогательный метод для определения високосного года
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Вспомогательный метод для нахождения самого взрослого животного
    private Animal findOldest() {
        Animal oldestAnimal = null;
        int maxAge = -1;

        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                int years = LocalDate.now().getYear() - animal.getDateOfBirth().getYear();
                if (years > maxAge) {
                    oldestAnimal = animal;
                    maxAge = years;
                }
            }
        }

        return oldestAnimal;
    }
}