package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

import java.time.LocalDate;
import java.util.*;

@Component
public class AnimalScheduler {
    private final AnimalsRepository animalsRepository;

    public AnimalScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    /**
     * Метод, вызываемый каждую минуту, который вызывает методы AnimalsRepository
     * и выводит результаты в стандартный вывод.
     */
    @Scheduled(fixedRate = 60000)
    public void executeScheduledTask() throws InterruptedException {
        System.out.println("\nFinding leap year names: ");
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        print(leapYearNames);
        System.out.println();

        System.out.println("Finding older animals than 7 years: ");
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(7);
        print(olderAnimals);
        System.out.println();

        System.out.println("Finding duplicate animals: ");
        animalsRepository.printDuplicate();
    }

    public static void print(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof List) {
                System.out.println("Type: " + key);
                printAnimalList((List<Animal>) value);
            } else if (value instanceof LocalDate) {
                System.out.println("Animal: " + key + ", Date of Birth: " + value);
            } else if (value instanceof Integer) {
                System.out.println("Animal: " + key + ", Age: " + value);
            }
        }
    }

    private static void printAnimalList(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("  Animal: " + animal);
        }
    }
}