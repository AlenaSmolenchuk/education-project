package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

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
        System.out.println("Finding leap year names: ");
        String[] leapYearNames = animalsRepository.findLeapYearNames();
        printNames(leapYearNames);
        System.out.println();

        System.out.println("Finding older animals than 7 years: ");
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(7);
        printAnimals(olderAnimals);
        System.out.println();

        System.out.println("Finding duplicate animals: ");
        animalsRepository.printDuplicate();
    }

    public static void printAnimals(Animal[] animals) {
        for (Animal animal : animals) {
            System.out.println("Animal: " + animal);
        }
    }

    private static void printNames(String[] names) {
        for (String name : names) {
            System.out.println("Leap Year Name: " + name);
        }
    }
}