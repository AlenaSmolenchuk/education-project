package ru.mts.educationproject.exception;

import java.io.FileNotFoundException;

public class FileException extends IllegalArgumentException {
    public FileException(String message) {
        super(message);
    }
}
