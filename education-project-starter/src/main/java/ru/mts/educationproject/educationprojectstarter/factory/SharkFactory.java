package ru.mts.educationproject.educationprojectstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания акул.
 */
@Component
public class SharkFactory implements AnimalFactory {

    @Value("${animal.shark.names}")
    private String[] sharkNames;

    public SharkFactory(String[] sharkNames) {
        this.sharkNames = sharkNames;
    }

    public SharkFactory() {
    }

    /**
     * Создает случайный объект акулы с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект акулы или дубликат объекта акулы в зависимости от вероятности.
     */
    @Override
    public Animal createRandomAnimal() {

        double duplicateProbability = 0.4;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            AnimalBreed breed = AnimalBreed.values()
                    [(int) (Math.random() * AnimalBreed.values().length)];
            String name = getRandomName(sharkNames);
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter character = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Shark(breed, name, cost, character, dateOfBirth);
        }
    }

    // Создает дубликат объекта акулы с предопределенными значениями.
    private Shark createDuplicate() {
        AnimalBreed breed = AnimalBreed.WHITE;
        String name = "Shark";
        BigDecimal cost = BigDecimal.valueOf(323.37);
        AnimalCharacter character = AnimalCharacter.PLAYFUL;
        LocalDate dateOfBirth = LocalDate.now().minusYears(16);

        return new Shark(breed, name, cost, character, dateOfBirth);
    }
}
