package ru.mts;

import ru.mts.model.animalint.Animal;
import ru.mts.service.CreateAnimalServiceImpl;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();

        // Вызов метода createAnimalsWithWhile
        System.out.println("Creating animals using while loop:");
        Set<Animal> animalsWhile = createAnimalServiceImpl.createAnimalsWithWhile(10);
        printAnimals(animalsWhile);
        System.out.println();

        // Вызов метода createAnimalsWithFor
        System.out.println("Creating animals using for loop:");
        Set<Animal> animalsFor = createAnimalServiceImpl.createAnimalsWithFor(5);
        printAnimals(animalsFor);
        System.out.println();

        // Вызов метода createRandomAnimal
        System.out.println("Creating random animal:");
        Animal randomAnimal = createAnimalServiceImpl.createRandomAnimal();
        System.out.println(randomAnimal + "\n");

    }

    private static void printAnimals(Set<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("Created: " + animal);
        }
    }
}