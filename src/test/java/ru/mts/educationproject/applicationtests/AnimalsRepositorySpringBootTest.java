package ru.mts.educationproject.applicationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.educationproject.config.TestConfig;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes  = TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositorySpringBootTest {

    @Autowired
    private AnimalsRepository animalsRepository;


    @Test
    public void testFindLeapYearNames() {
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();

        assertNotNull(leapYearNames);
        assertFalse(leapYearNames.isEmpty());
    }

    @Test
    public void testFindOlderAnimals() {
        int age = 1;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(age);
        assertNotNull(olderAnimals);
        assertFalse(olderAnimals.isEmpty());

        olderAnimals.values().forEach(animalAge -> assertThat(animalAge).isGreaterThan(age));
    }

    @Test
    public void testFindDuplicate() {
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();

        assertThat(duplicateAnimals).isNotNull();

        for (String animalType : duplicateAnimals.keySet()) {
            int duplicateCount = duplicateAnimals.get(animalType);

            assertThat(duplicateCount).isGreaterThanOrEqualTo(0);
        }
    }

    @Test
    public void testFindDuplicateWithEmptyList() {

        animalsRepository.setAnimals(Collections.emptyMap());

        Map<String, Integer> result = animalsRepository.findDuplicate();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testPrintDuplicate() {
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
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
}
