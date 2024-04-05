package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс Predator представляет хищных животных.
 */
public abstract class Predator extends AbstractAnimal {

    public Predator(AnimalBreed breed,
                    String name,
                    BigDecimal cost,
                    AnimalCharacter character,
                    LocalDate dateOfBirth,
                    String type,
                    String secretInfo) {
        super(breed, name, cost, character, dateOfBirth, type, secretInfo);
    }


    @Override
    public String toString() {
        return type + "{" +
                "breed=" + breed +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character=" + character +
                ", dateOfBirth=" + dateOfBirth +
                ", secretInfo='" + secretInfo + '\'' +
                '}';
    }
}
