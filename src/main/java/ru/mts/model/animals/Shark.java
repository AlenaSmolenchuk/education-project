package ru.mts.model.animals;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animaltypes.Predator;

import java.math.BigDecimal;

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
     */
    public Shark(String name, BigDecimal cost, AnimalCharacter character) {
        super("Shark", name, cost, character);
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
