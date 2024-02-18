package ru.mts.educationproject.applicationtests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.educationproject.config.TestConfig;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes  = TestConfig.class)
@ActiveProfiles("test")
public class AnimalsRepositorySpringBootTest {

    @Autowired
    private AnimalsRepository animalsRepository;

    @Test
    public void testFindLeapYearNames() {
        String[] leapYearNames = animalsRepository.findLeapYearNames();
        assertNotNull(leapYearNames);
        assertTrue(leapYearNames.length > 0);
    }

    @Test
    public void testFindOlderAnimal() {
        int age = 1;
        Animal[] olderAnimals = animalsRepository.findOlderAnimal(age);
        assertNotNull(olderAnimals);
        assertTrue(olderAnimals.length > 0);
    }

    @Test
    public void testPrintDuplicate() {
        assertDoesNotThrow(() -> animalsRepository.printDuplicate());
    }

    @Test
    public void testFindOlderAnimalWithBigAge() {
        int age = 200;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> animalsRepository.findOlderAnimal(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: 200");
    }

    @Test
    public void testFindOlderAnimalWithNegativeAge() {
        int age = -5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> animalsRepository.findOlderAnimal(age));
        assertThat(exception.getMessage()).isEqualTo("Unknown age format: -5");
    }
}
