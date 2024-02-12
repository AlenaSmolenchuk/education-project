package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacter.AnimalCharacter;

import java.math.BigDecimal;
import java.time.LocalDate;

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
     * @param dateOfBirth дата рождения животного
     */
    public Predator(String breed, String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super(breed, name, cost, character, dateOfBirth);
    }
}