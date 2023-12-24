package ru.mts.model.animaltypes;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Абстрактный класс AbstractAnimal реализует интерфейс Animal и предоставляет базовую реализацию методов.
 */
public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected AnimalCharacter character;

    /**
     * Конструктор для создания экземпляра абстрактного животного.
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param cost      стоимость животного
     * @param character характер животного
     */
    public AbstractAnimal(String breed, String name, BigDecimal cost, AnimalCharacter character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
    }

    // Реализация методов интерфейса Animal
    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public AnimalCharacter getCharacter() {
        return character;
    }
}

