package ru.mts.educationproject.educationprojectstarter.utilstarter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownAnimalTypeException;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animals.Dog;
import ru.mts.educationproject.educationprojectstarter.model.animals.Shark;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;
import ru.mts.educationproject.educationprojectstarter.model.animaltypes.AbstractAnimal;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AnimalDeserializer extends JsonDeserializer<AbstractAnimal> {

    @Override
    public AbstractAnimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        AnimalBreed breed = AnimalBreed.valueOf(node.get("breed").asText());
        String name = node.get("name").asText();
        BigDecimal cost = new BigDecimal(node.get("cost").asText());
        AnimalCharacter character = AnimalCharacter.valueOf(node.get("character").asText());
        LocalDate dateOfBirth = LocalDate.parse(node.get("dateOfBirth").asText());
        String type = node.get("type").asText();
        String secretInfo = node.get("secretInfo").asText();

        AbstractAnimal animal = null;

        if ("Dog".equals(type)) {
            animal = new Dog(breed, name, cost, character, dateOfBirth);
        } else if ("Shark".equals(type)) {
            animal = new Shark(breed, name, cost, character, dateOfBirth);
        } else if ("Wolf".equals(type)) {
            animal = new Wolf(breed, name, cost, character, dateOfBirth);
        } else {
            throw new UnknownAnimalTypeException("Unknown type: " + type);
        }

        return animal;
    }
}

