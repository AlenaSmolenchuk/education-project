package ru.mts.educationproject.educationprojectstarter.model.animals;

import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animaltypes.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ru.mts.educationproject.educationprojectstarter.utilstarter.StarterHelper.createSecret;

/**
 * Класс Wolf представляет волка.
 */
public class Wolf extends Predator {

    /**
     * Конструктор для создания экземпляра волка.
     *
     * @param breed     порода волка
     * @param name      имя волка
     * @param cost      стоимость волка
     * @param character характер волка
     * @param dateOfBirth день рождения волка
     */
    public Wolf(AnimalBreed breed,
                String name,
                BigDecimal cost,
                AnimalCharacter character,
                LocalDate dateOfBirth) {
        super(breed, name, cost, character, dateOfBirth, "Wolf", createSecret());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

