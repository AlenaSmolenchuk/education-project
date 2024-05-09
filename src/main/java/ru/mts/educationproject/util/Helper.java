package ru.mts.educationproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mts.educationproject.entity.Animal;

import java.util.Comparator;
import java.util.List;

public class Helper {

    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    // Вспомогательный метод для нахождения самого взрослого животного
    public static Animal findOldest(List<Animal> animalList) {
        return animalList.stream()
                .max(Comparator.comparingInt(Animal::getAge))
                .orElse(null);
    }
}
