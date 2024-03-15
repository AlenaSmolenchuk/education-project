package ru.mts.educationproject.educationprojectstarter.exceptionst;

public class UnknownCountOfAnimalException extends NumberFormatException {
    public UnknownCountOfAnimalException(String message) {
        super(message);
    }
}
