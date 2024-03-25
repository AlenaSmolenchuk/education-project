package ru.mts.educationproject.exception;

public class UnknownAgeFormatException extends NumberFormatException {
    public UnknownAgeFormatException(String message) {
        super(message);
    }
}

