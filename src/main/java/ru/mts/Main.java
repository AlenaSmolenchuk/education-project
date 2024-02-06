package ru.mts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.mts.config.AppConfig;
import ru.mts.model.animalint.Animal;
import ru.mts.repository.AnimalsRepository;

@ComponentScan("ru.mts")
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);

        System.out.println("\nFinding leap year names: ");
        String[] leapYearNames = animalsRepository.findLeapYearNames();
        printNames(leapYearNames);
        System.out.println();

        System.out.println("Finding older animals than 7 years: ");
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(7);
        printAnimals(olderAnimals);
        System.out.println();

        System.out.println("Finding duplicate animals: ");
        animalsRepository.printDuplicate();

        System.out.println();
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
