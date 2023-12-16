package ru.mts.model.animaltypes;

import ru.mts.model.animalcharacter.AnimalCharacter;

import java.math.BigDecimal;

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
     */
    public Pet(String breed, String name, BigDecimal cost, AnimalCharacter character) {
        super(breed, name, cost, character);
    }
}
