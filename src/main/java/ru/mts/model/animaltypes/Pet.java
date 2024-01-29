package ru.mts.model.animaltypes;

import ru.mts.model.animalcharacter.AnimalCharacter;

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
     */
    public Pet(String breed, String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super(breed, name, cost, character, dateOfBirth);
    }
}