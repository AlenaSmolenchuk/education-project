package ru.mts.factory;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания акул.
 */
public class SharkFactory implements AnimalFactory {
    @Override
    public Animal createRandomAnimal() {

        String name = "Shark" + System.currentTimeMillis();
        BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
        AnimalCharacter randomCharacter = AnimalCharacter.values()
                [(int) (Math.random() * AnimalCharacter.values().length)];
        LocalDate dateOfBirth = generateRandomDateOfBirth();

        return new Shark(name, cost, randomCharacter, dateOfBirth);
    }
}
