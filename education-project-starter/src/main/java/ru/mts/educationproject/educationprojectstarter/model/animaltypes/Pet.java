package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс Pet представляет домашних животных.
 */
public abstract class Pet extends AbstractAnimal {

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

