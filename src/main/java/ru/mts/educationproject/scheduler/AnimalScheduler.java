package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

import java.time.LocalDate;
import java.util.*;

import static ru.mts.educationproject.util.Helper.print;

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
}