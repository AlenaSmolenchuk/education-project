package ru.mts.educationproject.educationprojectstarter.model.animals;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacter.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animaltypes.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Класс Dog представляет собаку в качестве домашнего животного.
 */
public class Dog extends Pet {

    /**
     * Конструктор для создания экземпляра собаки.
     *
     * @param name        имя собаки
     * @param cost        стоимость собаки
     * @param character   характер собаки
     * @param dateOfBirth дата рождения собаки
     */
    public Dog(String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super("Dog", name, cost, character, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character=" + character +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
