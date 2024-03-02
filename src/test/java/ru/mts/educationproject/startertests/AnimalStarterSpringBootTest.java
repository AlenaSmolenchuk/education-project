package ru.mts.educationproject.startertests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.educationproject.config.TestConfig;
import ru.mts.educationproject.educationprojectstarter.factory.DogFactory;
import ru.mts.educationproject.educationprojectstarter.factory.SharkFactory;
import ru.mts.educationproject.educationprojectstarter.factory.WolfFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
public class AnimalStarterSpringBootTest {

    @Autowired
    private CreateAnimalService createAnimalService;

    @Autowired
    private WolfFactory testWolfFactory;

    @Autowired
    private DogFactory testDogFactory;

    @Autowired
    private SharkFactory testSharkFactory;

    @Test
    public void testCreateAnimalsWithDifferentTypes() {
        Map<String, List<Animal>> animals = createAnimalService.createAnimals(5);

        assertThat(animals).isNotNull();
        assertThat(animals.values().stream().mapToInt(List::size).sum()).isEqualTo(5);

        for (List<Animal> animalList : animals.values()) {
            for (Animal animal : animalList) {
                assertThat(animal).isInstanceOfAny(
                        testWolfFactory.createRandomAnimal().getClass(),
                        testDogFactory.createRandomAnimal().getClass(),
                        testSharkFactory.createRandomAnimal().getClass()
                );
            }
        }
    }

    @Test
    public void testCustomizeWolfNames() {
        String animalType = "Wolf";
        Animal animal = createAnimalService.createRandomAnimalByType(animalType);

        assertAll(
                () -> assertThat(animal).isNotNull(),
                () -> assertThat(animal.getName().toLowerCase()).isIn("wulf","walf","wolf")
        );

    }

    @Test
    public void testInitializeAnimalType() {
        String animalType = createAnimalService.initializeAnimalType();

        assertThat(animalType).isNotNull().isIn("Wolf", "Shark", "Dog");
    }

    @Test
    public void testCreateAnimalWithUnknownType() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> createAnimalService.createRandomAnimalByType("cat"));

        assertThat(exception.getMessage()).isEqualTo("Unknown animal type: cat");
    }

    @Test
    public void testCreateAnimalWithEmptyType() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> createAnimalService.createRandomAnimalByType(" "));

        assertThat(exception.getMessage()).isEqualTo("Unknown animal type:  ");
    }
}