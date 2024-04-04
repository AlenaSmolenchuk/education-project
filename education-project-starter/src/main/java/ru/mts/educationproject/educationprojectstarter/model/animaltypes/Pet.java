package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

/**
 * Абстрактный класс Pet представляет домашних животных.
 */
public abstract class Pet extends AbstractAnimal {

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

