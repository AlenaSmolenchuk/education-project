package ru.mts.model.animaltypes;

import ru.mts.model.animalcharacter.AnimalCharacter;

import java.math.BigDecimal;

/**
 * Абстрактный класс Predator представляет хищных животных.
 */
public abstract class Predator extends AbstractAnimal {
    /**
     * Конструктор для создания экземпляра хищного животного.
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param cost      стоимость животного
     * @param character характер животного
     */
    public Predator(String breed, String name, BigDecimal cost, AnimalCharacter character) {
        super(breed, name, cost, character);
    }
}
