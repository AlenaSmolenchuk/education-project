package ru.mts.model.animals;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animaltypes.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Wolf представляет волка.
 */
public class Wolf extends Predator {
    /**
     * Конструктор для создания экземпляра волка.
     *
     * @param name      имя волка
     * @param cost      стоимость волка
     * @param character характер волка
     * @param dateOfBirth день рождения волка
     */
    public Wolf(String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super("Wolf", name, cost, character, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}