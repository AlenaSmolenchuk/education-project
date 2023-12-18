package ru.mts.model.animals;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animaltypes.Pet;

import java.math.BigDecimal;

/**
 * Класс Dog представляет собаку в качестве домашнего животного.
 */
public class Dog extends Pet {
    /**
     * Конструктор для создания экземпляра собаки.
     *
     * @param name      имя собаки
     * @param cost      стоимость собаки
     * @param character характер собаки
     */
    public Dog(String name, BigDecimal cost, AnimalCharacter character) {
        super("Dog", name, cost, character);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
