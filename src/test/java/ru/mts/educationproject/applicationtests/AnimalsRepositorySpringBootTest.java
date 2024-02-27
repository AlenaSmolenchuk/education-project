package ru.mts.educationproject.applicationtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.educationproject.config.TestConfig;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Dog;
import ru.mts.educationproject.educationprojectstarter.model.animals.Shark;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.repository.AnimalsRepositoryImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositorySpringBootTest {

    @Autowired
    private AnimalsRepository animalsRepository;
    @Autowired
    private CreateAnimalService testCreateAnimalServiceMock;

    @BeforeEach
    public void setUp() {
        when(testCreateAnimalServiceMock.createAnimals(anyInt()))
                .thenReturn(createTestAnimals());

        animalsRepository.setAnimals(createTestAnimals());
    }

    @Test
    public void testFindLeapYearNames() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();

        assertThat(leapYearNames).hasSize(2);
        assertThat(leapYearNames)
                .containsEntry("Wolf wulf", LocalDate.of(2000, 1, 2))
                .containsEntry("Wolf wolf", LocalDate.of(2016, 12, 22));
    }

    @Test
    public void testFindOlderAnimals() {
        int age = 20;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(age);

        assertThat(olderAnimals).hasSize(3);
        assertThat(olderAnimals)
                .containsEntry(new Dog(
                        AnimalBreed.WHITE,
                        "dag",
                        BigDecimal.ZERO,
                        AnimalCharacter.CALM,
                        LocalDate.of(2001, 3, 3)), 23)
                .containsEntry(new Wolf(
                        AnimalBreed.BROWN,
                        "wulf",
                        BigDecimal.TEN,
                        AnimalCharacter.AGGRESSIVE,
                        LocalDate.of(2000, 1, 2)), 24)
                .containsEntry(new Dog(
                        AnimalBreed.WHITE,
                        "dug",
                        BigDecimal.ONE,
                        AnimalCharacter.PLAYFUL,
                        LocalDate.of(1999, 5, 14)), 25);
    }

    @Test
    public void testFindTheOldestAnimal() {
        int age = 70;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(age);

        assertThat(olderAnimals)
                .containsEntry(new Dog(
                        AnimalBreed.WHITE,
                        "dug",
                        BigDecimal.ONE,
                        AnimalCharacter.PLAYFUL,
                        LocalDate.of(1999, 5, 14)), 25);

    }

    @Test
    public void testFindDuplicate() {
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();

        assertThat(duplicateAnimals).isNotNull();
        assertThat(duplicateAnimals)
                .containsEntry("Shark", 2);
    }

    @Test
    public void testFindDuplicateWithEmptyList() {

        animalsRepository.setAnimals(Collections.emptyMap());

        Map<String, Integer> result = animalsRepository.findDuplicate();

        assertTrue(result.isEmpty());
        assertThat(result).hasSize(0);
    }

    @Test
    public void testPrintDuplicate() {
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
    }

    @Test
    public void testPrintDuplicateWithEmptyList() {
        animalsRepository.setAnimals(Collections.emptyMap());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        animalsRepository.printDuplicate();
        System.setOut(System.out);

        assertThat(outputStream.toString()).contains("No duplicate animals found.");
    }

    @Test
    public void testFindOlderAnimalsWithBigAge() {
        int age = 200;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> animalsRepository.findOlderAnimals(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: 200");
    }

    @Test
    public void testFindOlderAnimalsWithNegativeAge() {
        int age = -5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> animalsRepository.findOlderAnimals(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: -5");
    }

    private Map<String, List<Animal>> createTestAnimals() {
        Map<String, List<Animal>> testAnimals = new HashMap<>();

        List<Animal> wolfList = new ArrayList<>(
                List.of(new Wolf(AnimalBreed.BROWN,
                                "wulf",
                                BigDecimal.TEN,
                                AnimalCharacter.AGGRESSIVE,
                                LocalDate.of(2007, 1, 2)),
                        new Wolf(AnimalBreed.BROWN,
                                "wolf",
                                BigDecimal.ONE,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2016, 12, 22)),
                        new Wolf(AnimalBreed.BROWN,
                                "wulf",
                                BigDecimal.TEN,
                                AnimalCharacter.AGGRESSIVE,
                                LocalDate.of(2000, 1, 2)))
        );

        List<Animal> dogList = new ArrayList<>(
                List.of(new Dog(AnimalBreed.WHITE,
                                "dag",
                                BigDecimal.ZERO,
                                AnimalCharacter.CALM,
                                LocalDate.of(2001, 3, 3)),
                        new Dog(AnimalBreed.WHITE,
                                "dug",
                                BigDecimal.ONE,
                                AnimalCharacter.PLAYFUL,
                                LocalDate.of(1999, 5, 14)))
        );

        List<Animal> sharkList = new ArrayList<>(
                List.of(new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27)),
                        new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27)))
        );

        testAnimals.put("Wolf", wolfList);
        testAnimals.put("Dog", dogList);
        testAnimals.put("Shark", sharkList);

        return testAnimals;
    }
}
