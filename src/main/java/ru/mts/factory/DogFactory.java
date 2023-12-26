package ru.mts.factory;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания собак.
 */
public class DogFactory implements AnimalFactory {
    /**
     * Создает случайный объект собаки с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект собаки или дубликат объекта собаки в зависимости от вероятности.
     */
    @Override
    public Animal createRandomAnimal() {

        double duplicateProbability = 0.8;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            String name = "Dog" + System.currentTimeMillis();
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter randomCharacter = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Dog(name, cost, randomCharacter, dateOfBirth);
        }
    }


    // Создает дубликат объекта собаки с предопределенными значениями.
    private Dog createDuplicate() {
        String name = "Wolf";
        BigDecimal cost = BigDecimal.valueOf(500);
        AnimalCharacter character = AnimalCharacter.AGGRESSIVE;
        LocalDate dateOfBirth = LocalDate.now().minusYears(2);

        return new Dog(name, cost, character, dateOfBirth);
    }
}
