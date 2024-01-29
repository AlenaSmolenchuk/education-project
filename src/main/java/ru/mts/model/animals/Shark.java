package ru.mts.model.animals;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animaltypes.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Shark представляет акулу.
 */
public class Shark extends Predator {

    /**
     * Конструктор для создания экземпляра акулы.
     *
     * @param name      имя акулы
     * @param cost      стоимость акулы
     * @param character характер акулы
     * @param dateOfBirth день рождения акулы
     */
    public Shark(String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super("Shark", name, cost, character, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
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