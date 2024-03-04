package ru.mts.educationproject.util;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Helper {

    /**
     * Метод для нахождения возраста животного по его дате рождения
     *
     * @param birthDate  дата рождения животного.
     * @return возраст животного
     */
    public static int calculateAge (LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthDate.getYear();

        if (currentDate.getMonthValue() < birthDate.getMonthValue() ||
                (currentDate.getMonthValue() == birthDate.getMonthValue() &&
                        currentDate.getDayOfMonth() < birthDate.getDayOfMonth())) {
            age--;
        }

        return age;
    }

    public static void printAverage(double averageAge) {
        System.out.println("Average age of animals: " + averageAge);
    }

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
        if (animals.isEmpty()) {
            System.out.println("No animals for this type");
        }
        for (Animal animal : animals) {
            System.out.println("  Animal: " + animal);
        }
    }

    public static void printNames(List<String> names) {
        for (String name : names) {
            System.out.print(name + ", ");
        }
    }
}
