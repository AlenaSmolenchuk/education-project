package ru.mts;

import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalServiceImpl();

        // Вызов метода createAnimalsWithWhile
        System.out.println("Creating 10 animals using while loop:");
        createAnimalService.createAnimalsWithWhile(10);
        System.out.println();

        // Вызов метода createAnimalsWithFor
        System.out.println("Creating 5 animals using for loop:");
        createAnimalService.createAnimalsWithFor(5);
        System.out.println();

        // Вызов метода createRandomAnimal
        System.out.println("Creating random animal: " + "\n"
                + createAnimalService.createRandomAnimal());
        System.out.println();

    }
}
