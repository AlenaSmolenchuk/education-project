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
    private Animal[] animals;

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

        animals = createAnimalService.createAnimals(3);

        for (Animal animal : animals) {
            addAnimal(animal);
        }

        AnimalScheduler.printAnimals(animals);
    }

    /**
     * Метод поиска имен животных, родившихся в високосные годы.
     *
     * @return массив имен животных, родившихся в високосные годы
     */
    @Override
    public String[] findLeapYearNames() {
        List<String> leapYearNames = new ArrayList<>();
        for (Animal animal : animals) {
            if (isLeapYear(animal.getDateOfBirth().getYear())) {
                leapYearNames.add(animal.getName());
                System.out.println("Leap Year: " + animal.getDateOfBirth());
            }
        }
        return leapYearNames.toArray(new String[0]);
    }

    /**
     * Метод поиска животных, старше заданного возраста.
     *
     * @param age заданный возраст для поиска
     * @return массив животных, старше заданного возраста
     */
    @Override
    public Animal[] findOlderAnimal(int age) {
        List<Animal> olderAnimals = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Unknown age format: " + age);
        } else {
            for (Animal animal : animals) {
                int years = currentDate.getYear() - animal.getDateOfBirth().getYear();
                if (years > age) {
                    olderAnimals.add(animal);
                }
            }
            return olderAnimals.toArray(new Animal[0]);
        }
    }

    /**
     * Метод поиска дубликатов животных в хранилище.
     *
     * @return множество дубликатов животных
     */
    @Override
    public Set<Animal> findDuplicate() {
        Set<Animal> uniqueAnimals = new HashSet<>();
        Set<Animal> duplicateAnimals = new HashSet<>();

        for (Animal animal : animals) {
            if (!uniqueAnimals.add(animal)) {
                duplicateAnimals.add(animal);
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
        Set<Animal> duplicateAnimals = findDuplicate();
        if (!duplicateAnimals.isEmpty()) {
            System.out.println("Duplicate animals found:");
            for (Animal duplicateAnimal : duplicateAnimals) {
                System.out.println(duplicateAnimal);
            }
        } else {
            System.out.println("No duplicate animals found.");
        }
    }

    // Вспомогательный метод для определения високосного года
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Вспомогательный метод для добавления животного в хранилище
    private void addAnimal(Animal animal) {
        int index = 0;

        if (index < animals.length) {
            animals[index++] = animal;
        } else {
            System.out.println("Animal repository is full. Cannot add more animals.");
        }
    }
}