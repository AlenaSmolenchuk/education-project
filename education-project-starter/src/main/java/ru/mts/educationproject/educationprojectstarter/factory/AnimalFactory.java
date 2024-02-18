package ru.mts.educationproject.educationprojectstarter.factory;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.time.LocalDate;

/**
 * Объявление интерфейса фабрики для создания животных.
 */
public interface AnimalFactory {

    /**
     * Создает случайное животное.
     *
     * @return случайное животное
     */
    Animal createRandomAnimal();

    // Генерация случайной даты в пределах последних 20 лет
    default LocalDate generateRandomDateOfBirth() {

        long minDay = LocalDate.now().minusYears(20).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));

        return LocalDate.ofEpochDay(randomDay);
    }

    // Выбор случайного имени из массива
    default String getRandomName(String[] names) {
        return names[(int) (Math.random() * names.length)];
    }
}