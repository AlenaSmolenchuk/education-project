package ru.mts.factory;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания собак.
 */
public class DogFactory implements AnimalFactory {
    @Override
    public Animal createRandomAnimal() {

        String name = "Dog";
        BigDecimal cost = BigDecimal.valueOf(1000);
        AnimalCharacter сharacter = AnimalCharacter.CALM;
        LocalDate dateOfBirth = LocalDate.of(2022,2,2);

        return new Dog(name, cost, сharacter, dateOfBirth);
    }
}
