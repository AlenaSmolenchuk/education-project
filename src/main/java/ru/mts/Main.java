package ru.mts;

import ru.mts.model.animalint.Animal;
import ru.mts.service.CreateAnimalServiceImpl;
import ru.mts.service.SearchService;
import ru.mts.service.SearchServiceImpl;

public class Main {

    public static void main(String[] args) {
      
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();


        System.out.println("Creating animals: ");
        Animal[] animals = createAnimalServiceImpl.createAnimals(10);
        printAnimals(animals);
        System.out.println();


        // Вызов метода findLeapYearNames
        System.out.println("Finding leap year names: ");
        String[] leapYearNames = searchService.findLeapYearNames(animals);
        printNames(leapYearNames);
        System.out.println();

        // Вызов метода findOlderAnimal
        System.out.println("Finding older animals than 7 years: ");
        Animal[] olderAnimals = searchService.findOlderAnimal(animals, 7);
        printAnimals(olderAnimals);
        System.out.println();

        // Вызов метода findDuplicate
        System.out.println("Finding duplicate animals: ");
        searchService.printDuplicate(searchService.findDuplicate(animals));
        System.out.println();
      
    }

    private static void printAnimals(Animal[] animals) {
        for (Animal animal : animals) {
            System.out.println("Created: " + animal);
        }
    }


    private static void printNames(String[] names) {
        for (String name : names) {
            System.out.println("Leap Year Name: " + name);
        }
    }
}