package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс Pet представляет домашних животных.
 */
public abstract class Pet extends AbstractAnimal {

    /**
     * Конструктор для создания экземпляра домашнего животного.
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param cost      стоимость животного
     * @param character характер животного
     * @param dateOfBirth дата рождения животного
     * @param type тип животного
     */
    public Pet(AnimalBreed breed,
               String name,
               BigDecimal cost,
               AnimalCharacter character,
               LocalDate dateOfBirth,
               String type) {
        super(breed, name, cost, character, dateOfBirth, type);
    }
}

