package ru.mts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.model.animalcharacter.AnimalCharacter;
import ru.mts.model.animalint.Animal;
import ru.mts.model.animals.Dog;
import ru.mts.model.animals.Shark;
import ru.mts.model.animals.Wolf;
import ru.mts.service.SearchService;
import ru.mts.service.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса SearchServiceImpl.
 */
@DisplayName("SearchServiceImpl Tests")
class SearchServiceImplTest {


    /**
     * Вложенный класс тестов для сравнения объектов Animal с использованием метода equals.
     */
    @Nested
    @DisplayName("Tests Animals comparison")
    class AnimalsEqualsTests {

        /**
         * Тест для проверки корректности работы метода equals для объектов Animal.
         */
        @Test
        @DisplayName("Test Equals for Animal Objects")
        void shouldTestEqualsForAnimalObjects() {
            Animal animal1 = new Dog(
                    "Animal1",
                    BigDecimal.TEN,
                    AnimalCharacter.AGGRESSIVE,
                    LocalDate.of(2000, 1, 1));
            Animal animal2 = new Dog(
                    "Animal1",
                    BigDecimal.TEN,
                    AnimalCharacter.AGGRESSIVE,
                    LocalDate.of(2000, 1, 1));

            assertAll(
                    () -> assertEquals(animal1, animal2)
            );
        }

        /**
         * Тест для проверки корректности работы метода equals для объектов Animal, которые не равны.
         */
        @Test
        @DisplayName("Test Inequality for Animal Objects")
        void shouldTestInequalityForAnimalObjects() {
            Animal animal1 = new Dog(
                    "Animal1",
                    BigDecimal.TEN,
                    AnimalCharacter.AGGRESSIVE,
                    LocalDate.of(2000, 1, 1));
            Animal animal2 = new Dog(
                    "Animal1",
                    BigDecimal.TEN,
                    AnimalCharacter.AGGRESSIVE,
                    LocalDate.of(2000, 2, 1));

            assertAll(
                    () -> assertNotEquals(animal1, animal2)
            );
        }
    }

    /**
     * Вложенный класс тестов для методов, работающих с массивами объектов Animal.
     */
    @Nested
    @DisplayName("Tests for arrays of Animals")
    class AnimalsTests {

        private SearchService searchService;

        @BeforeEach
        void setUp() {
            searchService = new SearchServiceImpl();
        }

        /**
         * Проверяет, что метод findLeapYearNames возвращает корректные имена животных,
         * родившихся в високосные годы.
         */
        @Test
        @DisplayName("Return Names of Animals Born in Leap Years")
        void shouldReturnNamesOfAnimalsBornInLeapYears() {
            Animal[] animals = createAnimals();

            String[] leapYearNames = searchService.findLeapYearNames(animals);
            String[] expectedLeapYearNames = {"Animal1", "Animal2", "Animal4", "Animal1", "Animal4"};

            assertAll(
                    () -> assertNotNull(leapYearNames),
                    () -> assertArrayEquals(expectedLeapYearNames, leapYearNames)
            );
        }


        /**
         * Проверяет, что метод findLeapYearNames возвращает пустой массив для животных,
         * родившихся не в високосные годы.
         */
        @Test
        @DisplayName("Return Empty Array for Animals Not Born in Leap Years")
        void shouldReturnEmptyArrayForAnimalsNotBornInLeapYears() {

            Animal[] animals = createNonLeapYearAnimals();

            String[] leapYearNames = searchService.findLeapYearNames(animals);

            assertAll(
                    () -> assertNotNull(leapYearNames),
                    () -> assertEquals(0, leapYearNames.length)
            );
        }

        /**
         * Проверяет, что метод findOlderAnimal возвращает корректный массив животных
         * при заданном массиве и параметре возраста.
         */
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3}) // Пример значений параметра возраста для теста
        @DisplayName("Return Correct Array of Older Animals")
        void shouldReturnCorrectArrayForOlderAnimals(int age) {

            Animal[] animals = createAnimals();

            Animal[] olderAnimals = searchService.findOlderAnimal(animals, age);

            Arrays.stream(olderAnimals)
                    .forEach(animal -> assertTrue(isOlderThan(animal, age)));
        }

        /**
         * Проверяет, что метод findDuplicate возвращает корректное множество дубликатов животных.
         */
        @Test
        @DisplayName("Return Duplicate Animals")
        void shouldReturnDuplicateAnimals() {
            Animal[] animals = createAnimals();

            Set<Animal> duplicateAnimals = searchService.findDuplicate(animals);

            assertAll(
                    () -> assertNotNull(duplicateAnimals),
                    () -> assertEquals(2, duplicateAnimals.size()),
                    () -> assertTrue(duplicateAnimals.contains(new Dog(
                            "Animal1",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2000, 1, 1)
                    ))),
                    () -> assertTrue(duplicateAnimals.contains(new Wolf(
                            "Animal4",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2008, 1, 1)
                    )))
            );
        }


        // Дополнительный метод для проверки, что животное старше заданного возраста
        private boolean isOlderThan(Animal animal, int age) {
            LocalDate currentDate = LocalDate.now();
            int years = currentDate.getYear() - animal.getDateOfBirth().getYear();
            return years > age;
        }


        //Создает массив животных для целей тестирования.
        private Animal[] createAnimals() {
            return new Animal[] {
                    new Dog(
                            "Animal1",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2000, 1, 1)),
                    new Dog(
                            "Animal2",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2004, 1, 1)),
                    new Shark(
                            "Animal3",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2010, 1, 1)),
                    new Wolf(
                            "Animal4",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2008, 1, 1)),
                    new Shark(
                            "Animal5",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2005, 1, 1)),
                    new Dog(
                            "Animal1",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2000, 1, 1)),
                    new Wolf(
                            "Animal4",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2008, 1, 1))
            };
        }

        //Создает массив животных(родившихся не в високосный год) для целей тестирования.
        private Animal[] createNonLeapYearAnimals() {
            return new Animal[]{
                    new Shark(
                            "Animal5",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2005, 1, 1)),
                    new Shark(
                            "Animal6",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(2007, 1, 1)),
                    new Shark(
                            "Animal8",
                            BigDecimal.TEN,
                            AnimalCharacter.AGGRESSIVE,
                            LocalDate.of(1995, 1, 1))
            };
        }
    }
}