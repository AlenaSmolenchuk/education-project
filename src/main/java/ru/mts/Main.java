package ru.mts;

import ru.mts.model.animalint.Animal;
import ru.mts.service.CreateAnimalServiceImpl;
import ru.mts.service.SearchService;
import ru.mts.service.SearchServiceImpl;

public class Main {

    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();

        // Вызов метода createAnimalsWithWhile
        System.out.println("Creating animals using while loop:");
        Animal[] animalsWhile = createAnimalServiceImpl.createAnimals(10);
        printAnimals(animalsWhile);
        System.out.println();

        // Вызов метода createAnimalsWithFor
        System.out.println("Creating 10 animals using for loop:");
        Animal[] animalsFor = createAnimalServiceImpl.createAnimals();
        printAnimals(animalsFor);
        System.out.println();

        // Вызов метода findLeapYearNames для массива animalsWhile
        System.out.println("Finding leap year names in the WhileArray:");
        String[] leapYearNamesWhile = searchService.findLeapYearNames(animalsWhile);
        printNames(leapYearNamesWhile);
        System.out.println();

        // Вызов метода findOlderAnimal для массива animalsWhile
        System.out.println("Finding older animals than 14 years in the WhileArray:");
        Animal[] olderAnimalsWhile = searchService.findOlderAnimal(animalsWhile, 14);
        printAnimals(olderAnimalsWhile);
        System.out.println();

        // Вызов метода findDuplicate для массива animalsWhile
        System.out.println("Finding duplicate animals in the WhileArray:");
        searchService.findDuplicate(animalsWhile);
        System.out.println();

        // Вызов метода findLeapYearNames для массива animalsFor
        System.out.println("Finding leap year names in the ForArray:");
        String[] leapYearNamesFor = searchService.findLeapYearNames(animalsFor);
        printNames(leapYearNamesFor);
        System.out.println();

        // Вызов метода findOlderAnimal для массива animalsFor
        System.out.println("Finding older animals than 14 years in the ForArray:");
        Animal[] olderAnimalsFor = searchService.findOlderAnimal(animalsFor, 14);
        printAnimals(olderAnimalsFor);
        System.out.println();

        // Вызов метода findDuplicate для массива animalsFor
        System.out.println("Finding duplicate animals in the ForArray:");
        searchService.findDuplicate(animalsFor);
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