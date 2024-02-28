package ru.mts.educationproject.util;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Helper {
    public static void print(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof List) {
                System.out.println("Type: " + key);
                printAnimalList((List<Animal>) value);
            } else if (value instanceof LocalDate) {
                System.out.println("Animal: " + key + ", Date of Birth: " + value);
            } else if (value instanceof Integer) {
                System.out.println("Animal: " + key + ", Age: " + value);
            } else {
                throw new IllegalArgumentException("Unexpected value type for value : " + value
                        + " or key: " + key);
            }
        }
    }

    public static void printAnimalList(List<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println("  Animal: " + animal);
        }
    }
}
