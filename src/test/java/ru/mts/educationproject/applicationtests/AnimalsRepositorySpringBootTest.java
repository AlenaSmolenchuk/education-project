package ru.mts.educationproject.applicationtests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.educationproject.config.TestConfig;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Dog;
import ru.mts.educationproject.educationprojectstarter.model.animals.Shark;
import ru.mts.educationproject.educationprojectstarter.model.animals.Wolf;
import ru.mts.educationproject.educationprojectstarter.service.CreateAnimalService;
import ru.mts.educationproject.exception.AnimalsArrayException;
import ru.mts.educationproject.exception.UnknownAgeFormatException;
import ru.mts.educationproject.repository.AnimalsRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static ru.mts.educationproject.util.Helper.calculateAge;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositorySpringBootTest {

    @Autowired
    private AnimalsRepository animalsRepository;

    @MockBean
    private CreateAnimalService testCreateAnimalServiceMock;

    Map<String, List<Animal>> testAnimals = new ConcurrentHashMap<>();

    @BeforeEach
    public void setUp() {
        when(testCreateAnimalServiceMock.createAnimals(anyInt()))
                .thenReturn(createTestAnimals());

        animalsRepository.initAnimals();
    }

    @Test
    public void testFindLeapYearNames() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();

        assertThat(leapYearNames).hasSize(3);
        assertThat(leapYearNames)
                .containsEntry("Wolf wulf", LocalDate.of(2000, 1, 2))
                .containsEntry("Wolf wolf", LocalDate.of(2016, 12, 22))
                .containsEntry("Dog dug", LocalDate.of(2000, 5, 14));
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
                        LocalDate.of(2000, 5, 14)), 23);
    }

    @Test
    public void testFindTheOldestAnimal() {
        int age = 70;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(age);
        assertThat(olderAnimals).hasSize(1);
        assertThat(olderAnimals)
                .containsEntry(new Wolf(
                        AnimalBreed.BROWN,
                        "wulf",
                        BigDecimal.TEN,
                        AnimalCharacter.AGGRESSIVE,
                        LocalDate.of(2000, 1, 2)), 24);
    }

    @Test
    public void testFindDuplicate() {
        Map<String, List<Animal>> duplicateAnimals = animalsRepository.findDuplicate();

        animalsRepository.printDuplicate();
        assertThat(duplicateAnimals).isNotNull();
        assertThat(duplicateAnimals).hasSize(1);
        assertThat(duplicateAnimals)
                .containsEntry("Shark shark BLACK FRIENDLY 2009-03-27 10.00", List.of(
                        new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27)),
                        new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27))));
    }

    @Test
    public void testFindDuplicateWithEmptyList() {

        animalsRepository.setAnimals(Collections.emptyMap());

        Map<String, List<Animal>> result = animalsRepository.findDuplicate();

        assertTrue(result.isEmpty());
        assertThat(result).hasSize(0);
    }

    @Test
    public void testPrintDuplicate() {
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
    }

    @Test
    public void testFindOlderAnimalsWithBigAge() {
        int age = 200;

        UnknownAgeFormatException exception = assertThrows(UnknownAgeFormatException.class,
                () -> animalsRepository.findOlderAnimals(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: 200");
    }

    @Test
    public void testFindOlderAnimalsWithNegativeAge() {
        int age = -5;

        UnknownAgeFormatException exception = assertThrows(UnknownAgeFormatException.class,
                () -> animalsRepository.findOlderAnimals(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: -5");
    }

    @Test
    public void testCalculateAverageCost() {
        BigDecimal averageCost = animalsRepository.calculateAverageCost(testAnimals);

        assertThat(averageCost).isEqualByComparingTo(BigDecimal.valueOf(6.00));
    }

    @Test
    public void testFindOldAndExpensive() {
        List<Animal> result = animalsRepository.findOldAndExpensive();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(4);
        assertThat(result)
                .contains(
                        new Wolf(AnimalBreed.BROWN,
                                "wulf",
                                BigDecimal.TEN,
                                AnimalCharacter.AGGRESSIVE,
                                LocalDate.of(2007, 1, 2)),
                        new Wolf(AnimalBreed.BROWN,
                                "wulf",
                                BigDecimal.TEN,
                                AnimalCharacter.AGGRESSIVE,
                                LocalDate.of(2000, 1, 2)),
                        new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27)),
                        new Shark(AnimalBreed.BLACK,
                                "shark",
                                BigDecimal.TEN,
                                AnimalCharacter.FRIENDLY,
                                LocalDate.of(2009, 3, 27))
                );
    }

    @Test
    public void testFindMinCostAnimals() throws AnimalsArrayException {
        List<String> result = animalsRepository.findMinCostAnimals();
        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly("wolf", "dug", "dag");
        assertDoesNotThrow(() -> animalsRepository.findMinCostAnimals());
    }

    @Test
    public void testFindMinCostAnimalsWithNullMap() {
        animalsRepository.setAnimals(Collections.emptyMap());

        AnimalsArrayException exception = assertThrows(AnimalsArrayException.class,
                () -> animalsRepository.findMinCostAnimals());
        assertThat(exception.getMessage())
                .isEqualTo("The 'animals' map is null or contains less than 3 elements.");
    }

    private Map<String, List<Animal>> createTestAnimals() {

        List<Animal> wolfList = new CopyOnWriteArrayList<>(
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

        List<Animal> dogList = new CopyOnWriteArrayList<>(
                List.of(new Dog(AnimalBreed.WHITE,
                                "dag",
                                BigDecimal.ZERO,
                                AnimalCharacter.CALM,
                                LocalDate.of(2001, 3, 3)),
                        new Dog(AnimalBreed.WHITE,
                                "dug",
                                BigDecimal.ONE,
                                AnimalCharacter.PLAYFUL,
                                LocalDate.of(2000, 5, 14)))
        );

        List<Animal> sharkList = new CopyOnWriteArrayList<>(
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
