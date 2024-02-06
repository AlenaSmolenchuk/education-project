package ru.mts.factory;

import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания акул.
 */
public class SharkFactory implements AnimalFactory {
    /**
     * Создает случайный объект акулы с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект акулы или дубликат объекта акулы в зависимости от вероятности.
     */
    @Override
    public Animal createRandomAnimal() {

        double duplicateProbability = 0.7;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            String name = "Shark" + System.currentTimeMillis();
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter randomCharacter = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Shark(name, cost, randomCharacter, dateOfBirth);
        }
    }


    // Создает дубликат объекта акулы с предопределенными значениями.
    private Shark createDuplicate() {
        String name = "Shark";
        BigDecimal cost = BigDecimal.valueOf(323.37);
        AnimalCharacter character = AnimalCharacter.PLAYFUL;
        LocalDate dateOfBirth = LocalDate.now().minusYears(16);

        return new Shark(name, cost, character, dateOfBirth);
    }
}