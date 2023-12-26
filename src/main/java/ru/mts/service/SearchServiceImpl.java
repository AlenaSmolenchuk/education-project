package ru.mts.service;

import ru.mts.model.animalint.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Реализация интерфейса SearchService для поиска животных по различным критериям.
 */
public class SearchServiceImpl implements SearchService {

    /**
     * Находит имена животных, родившихся в високосный год.
     *
     * @param animals массив животных
     * @return массив имен животных, родившихся в високосный год
     */
    @Override
    public String[] findLeapYearNames(Animal[] animals) {
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
     * Находит животных, чей возраст старше указанного числа лет.
     *
     * @param animals массив животных
     * @param age     возраст, сравниваемый с возрастом животных
     * @return массив животных, чей возраст старше указанного числа лет
     */
    @Override
    public Animal[] findOlderAnimal(Animal[] animals, int age) {
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

    /**
     * Выводит на экран дубликаты животных в массиве.
     *
     * @param animals массив животных
     */
    @Override
    public void findDuplicate(Animal[] animals) {
        Set<Animal> uniqueAnimals = new HashSet<>();
        Set<Animal> duplicateAnimals = new HashSet<>();

        for (Animal animal : animals) {
            if (!uniqueAnimals.add(animal)) {
                duplicateAnimals.add(animal);
            }
        }

        if (!duplicateAnimals.isEmpty()) {
            System.out.println("Duplicate animals found:");
            for (Animal duplicateAnimal : duplicateAnimals) {
                System.out.println(duplicateAnimal);
            }
        } else {
            System.out.println("No duplicate animals found.");
        }
    }

    // Проверяет, является ли указанный год високосным.
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}