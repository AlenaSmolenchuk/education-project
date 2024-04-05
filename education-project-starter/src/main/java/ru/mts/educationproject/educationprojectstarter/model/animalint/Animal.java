package ru.mts.educationproject.educationprojectstarter.model.animalint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.utilstarter.AnimalDeserializer;
import ru.mts.educationproject.educationprojectstarter.utilstarter.Base64Deserializer;
import ru.mts.educationproject.educationprojectstarter.utilstarter.Base64Serializer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация интерфейса Animal представляет общие методы для всех животных.
 */
@JsonDeserialize(using = AnimalDeserializer.class)
public interface Animal {

    /**
     * Возвращает породу животного.
     *
     * @return порода животного
     */
    AnimalBreed getBreed();

    /**
     * Возвращает имя животного.
     *
     * @return имя животного
     */
    String getName();

    /**
     * Возвращает стоимость животного в магазине.
     *
     * @return стоимость животного
     */
    BigDecimal getCost();

    /**
     * Возвращает характер животного.
     *
     * @return характер животного
     */
    AnimalCharacter getCharacter();

    /**
     * Возвращает дату рождения животного.
     *
     * @return дата рождения
     */
    LocalDate getDateOfBirth();

    /**
     * Возвращает тип животного.
     *
     * @return тип животного
     */
    String getType();

    /**
     * Возвращает секретную информацию о животном.
     *
     * @return секретная информация
     */
    String getSecretInfo();
}

