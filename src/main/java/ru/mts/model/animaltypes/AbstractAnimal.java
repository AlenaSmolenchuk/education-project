package ru.mts.model.animaltypes;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс AbstractAnimal реализует интерфейс Animal и предоставляет базовую реализацию методов.
 */
public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected AnimalCharacter character;
    protected LocalDate dateOfBirth;

    /**
     * Конструктор для создания экземпляра абстрактного животного.
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param cost      стоимость животного
     * @param character характер животного
     * @param dateOfBirth дата рождения животного
     */
    public AbstractAnimal(String breed,
                          String name,
                          BigDecimal cost,
                          AnimalCharacter character,
                          LocalDate dateOfBirth) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    // Переопределение метода equals()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed)
                && Objects.equals(name, that.name)
                && Objects.equals(cost, that.cost)
                && character == that.character
                && Objects.equals(dateOfBirth, that.dateOfBirth);
    }
}