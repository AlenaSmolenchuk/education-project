package ru.mts.educationproject.educationprojectstarter.model.animalint;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacter.AnimalCharacter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Объявление интерфейса Animal представляет общие методы для всех животных.
 */
public interface Animal {

    /**
     * Возвращает породу животного.
     *
     * @return порода животного
     */
    String getBreed();

    /**
     * Возвращает имя животного.
     *
     * @return имя животного
     */
    String getName();

    /**
     * Возвращает стоимость животного в магазине.
     *
     * @return стоимость животного
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного.
     *
     * @return характер животного
     */
    AnimalCharacter getCharacter();

    /**
     * Возвращает дату рождения животного.
     *
     * @return дата рождения
     */
    LocalDate getDateOfBirth();

}

