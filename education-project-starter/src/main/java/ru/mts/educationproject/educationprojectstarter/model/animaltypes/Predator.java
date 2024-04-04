package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

/**
 * Абстрактный класс Predator представляет хищных животных.
 */
public abstract class Predator extends AbstractAnimal {

    @Override
    public String toString() {
        return type + "{" +
                "breed=" + breed +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character=" + character +
                ", dateOfBirth=" + dateOfBirth +
                ", secretInfo='" + secretInfo + '\'' +
                '}';
    }
}
