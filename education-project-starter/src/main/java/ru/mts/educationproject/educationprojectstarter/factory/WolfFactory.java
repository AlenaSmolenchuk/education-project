package ru.mts.educationproject.educationprojectstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacter.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания волков.
 */
public class WolfFactory implements AnimalFactory {

    @Value("${animal.wolf.names}")
    private String wolfNames;

    /**
     * Создает случайный объект волка с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект волка или дубликат объекта волка в зависимости от вероятности.
     */
    public Animal createRandomWolf() {

        double duplicateProbability = 0.9;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            String name = getRandomName(wolfNames);
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter randomCharacter = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Wolf(name, cost, randomCharacter, dateOfBirth);
        }
    }


    // Создает дубликат объекта волка с предопределенными значениями.
    private Wolf createDuplicate() {
        String name = "Wolf";
        BigDecimal cost = BigDecimal.valueOf(500.9786);
        AnimalCharacter character = AnimalCharacter.AGGRESSIVE;
        LocalDate dateOfBirth = LocalDate.now().minusYears(2);

        return new Wolf(name, cost, character, dateOfBirth);
    }
}