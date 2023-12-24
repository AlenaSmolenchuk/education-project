package ru.mts.model.animalint;


import ru.mts.model.animalcharacter.AnimalCharacter;

import java.math.BigDecimal;

/**
 * Реализация интерфейса Animal представляет общие методы для всех животных.
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
}
