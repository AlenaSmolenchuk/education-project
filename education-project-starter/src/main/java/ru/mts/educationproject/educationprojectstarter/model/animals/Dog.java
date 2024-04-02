package ru.mts.educationproject.educationprojectstarter.model.animals;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
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
     * @param breed       порода собаки
     * @param name        имя собаки
     * @param cost        стоимость собаки
     * @param character   характер собаки
     * @param dateOfBirth дата рождения собаки
     */
    public Dog(AnimalBreed breed,
               String name,
               BigDecimal cost,
               AnimalCharacter character,
               LocalDate dateOfBirth) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.dateOfBirth = dateOfBirth;
        this.type = "Dog";
    }

    public Dog() {
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
