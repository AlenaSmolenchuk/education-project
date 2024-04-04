package ru.mts.educationproject.educationprojectstarter.model.animalint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.utilstarter.Base64Deserializer;
import ru.mts.educationproject.educationprojectstarter.utilstarter.Base64Serializer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация интерфейса Animal представляет общие методы для всех животных.
 */
public interface Animal {

    /**
     * Возвращает породу животного.
     *
     * @return порода животного
     */
    @JsonProperty("breed")
    AnimalBreed getBreed();

    /**
     * Возвращает имя животного.
     *
     * @return имя животного
     */
    @JsonProperty("name")
    String getName();

    /**
     * Возвращает стоимость животного в магазине.
     *
     * @return стоимость животного
     */
    @JsonProperty("cost")
    BigDecimal getCost();

    /**
     * Возвращает характер животного.
     *
     * @return характер животного
     */
    @JsonProperty("character")
    AnimalCharacter getCharacter();

    /**
     * Возвращает дату рождения животного.
     *
     * @return дата рождения
     */
    @JsonProperty("dateOfBirth")
    LocalDate getDateOfBirth();

    /**
     * Возвращает тип животного.
     *
     * @return тип животного
     */
    @JsonProperty("type")
    String getType();

    /**
     * Возвращает секретную информацию о животном.
     *
     * @return секретная информация
     */
    @JsonProperty("secretInfo")
    @JsonDeserialize(using = Base64Deserializer.class)
    @JsonSerialize(using = Base64Serializer.class)
    String getSecretInfo();
}

