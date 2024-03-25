package ru.mts.educationproject.educationprojectstarter.exceptionst;

public class UnknownAnimalTypeException extends IllegalArgumentException {
    public UnknownAnimalTypeException(String message) {
        super(message);
    }
}