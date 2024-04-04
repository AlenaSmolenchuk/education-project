package ru.mts.educationproject.educationprojectstarter.model.animals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animaltypes.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ru.mts.educationproject.educationprojectstarter.utilstarter.StarterHelper.createSecret;

/**
 * Класс Shark представляет акулу.
 */
public class Shark extends Predator {

    /**
     * Конструктор для создания экземпляра акулы.
     *
     * @param breed       порода акулы
     * @param name        имя акулы
     * @param cost        стоимость акулы
     * @param character   характер акулы
     * @param dateOfBirth день рождения акулы
     */
    public Shark(AnimalBreed breed,
                 String name,
                 BigDecimal cost,
                 AnimalCharacter character,
                 LocalDate dateOfBirth) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.dateOfBirth = dateOfBirth;
        this.type = "Shark";
        this.secretInfo = createSecret();
    }

    public Shark() {
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

