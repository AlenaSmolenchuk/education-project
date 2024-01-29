package ru.mts;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class AnimalMock implements Animal {
    private String name;
    private LocalDate dateOfBirth;

    private String breed;

    private BigDecimal cost;

    private AnimalCharacter character;

    public AnimalMock(String name, int birthYear) {
        this.name = name;
        this.dateOfBirth = LocalDate.of(birthYear, 1, 1); // Устанавливаем произвольный месяц и день
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalMock that = (AnimalMock) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(dateOfBirth, that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public String toString() {
        return  name + ", " + dateOfBirth.getYear();
    }
}

