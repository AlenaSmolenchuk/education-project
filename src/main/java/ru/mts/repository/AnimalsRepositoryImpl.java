package ru.mts.repository;

import ru.mts.Main;
import ru.mts.model.animalint.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalService createAnimalService;
    private Animal[] animals;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void initAnimals() {
        System.out.println("Creating animals:");

        animals = createAnimalService.createAnimals(10);

        for (Animal animal : animals) {
            addAnimal(animal);
        }

        Main.printAnimals(animals);
    }

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

    @Override
    public Animal[] findOlderAnimal(int age) {
        List<Animal> olderAnimals = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Animal animal : animals) {
            int years = currentDate.getYear() - animal.getDateOfBirth().getYear();
            if (years > age) {
                olderAnimals.add(animal);
            }
        }
        return olderAnimals.toArray(new Animal[0]);
    }

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
     * Выводит на экран дубликаты животных из множества.
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

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private void addAnimal(Animal animal) {
        int index = 0;

        if (index < animals.length) {
            animals[index++] = animal;
        } else {
            System.out.println("Animal repository is full. Cannot add more animals.");
        }
    }
}
