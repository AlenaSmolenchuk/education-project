package ru.mts.educationproject.educationprojectstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания волков.
 */
@Component
public class WolfFactory implements AnimalFactory {

    @Value("${animal.wolf.names}")
    private String[] wolfNames;

    /**
     * Создает случайный объект волка с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект волка или дубликат объекта волка в зависимости от вероятности.
     */
    @Override
    public Animal createRandomAnimal() {

        double duplicateProbability = 0.5;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            AnimalBreed breed = AnimalBreed.values()
                    [(int) (Math.random() * AnimalBreed.values().length)];
            String name = getRandomName(wolfNames);
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter character = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Wolf(breed, name, cost, character, dateOfBirth);
        }
    }

    // Создает дубликат объекта волка с предопределенными значениями.
    private Wolf createDuplicate() {
        AnimalBreed breed = AnimalBreed.BLACK;
        String name = "Wolf";
        BigDecimal cost = BigDecimal.valueOf(500.9786);
        AnimalCharacter character = AnimalCharacter.AGGRESSIVE;
        LocalDate dateOfBirth = LocalDate.now().minusYears(2);

        return new Wolf(breed, name, cost, character, dateOfBirth);
    }
}