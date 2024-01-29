package ru.mts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.model.animalint.Animal;
import ru.mts.service.SearchService;
import ru.mts.service.SearchServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
            Animal animal1 = new AnimalMock("Animal1", 2000);
            Animal animal2 = new AnimalMock("Animal1", 2000);

            assertAll(
                    () -> assertEquals(animal1, animal2),
                    () -> assertEquals(animal2, animal1)
            );
        }

        /**
         * Тест для проверки корректности работы метода equals для объектов Animal, которые не равны.
         */
        @Test
        @DisplayName("Test Inequality for Animal Objects")
        void shouldTestInequalityForAnimalObjects() {
            Animal animal1 = new AnimalMock("Animal1", 2000);
            Animal animal2 = new AnimalMock("Animal3", 2010);

            assertAll(
                    () -> assertNotEquals(animal1, animal2),
                    () -> assertNotEquals(animal2, animal1)
            );
        }
    }

    /**
     * Вложенный класс тестов для методов, работающих с массивами объектов Animal.
     */
    @Nested
    @DisplayName("Tests for arrays of Animals")
    class AnimalsTests {

        /**
         * Проверяет, что метод findLeapYearNames возвращает корректные имена животных,
         * родившихся в високосные годы.
         */
        @Test
        @DisplayName("Return Names of Animals Born in Leap Years")
        void shouldReturnNamesOfAnimalsBornInLeapYears() {
            Animal[] animals = createAnimals();
            SearchService searchService = new SearchServiceImpl();

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
            SearchService searchService = new SearchServiceImpl();

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
            SearchService searchService = new SearchServiceImpl();

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
            SearchService searchService = new SearchServiceImpl();

            Set<Animal> duplicateAnimals = searchService.findDuplicate(animals);

            assertAll(
                    () -> assertNotNull(duplicateAnimals),
                    () -> assertEquals(2, duplicateAnimals.size())
            );
        }

        /**
         * Проверяет, что метод printDuplicate выводит на экран корректное сообщение о дубликатах животных.
         */
        @Test
        @DisplayName("Print Duplicate Animals")
        void shouldPrintDuplicateAnimals() {
            Animal[] animals = createAnimals();
            SearchService searchService = new SearchServiceImpl();

            Set<Animal> duplicateAnimals = searchService.findDuplicate(animals);

            // Redirect System.out to capture printed output
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            searchService.printDuplicate(duplicateAnimals);

            String expectedOutput = "Duplicate animals found:\r\n" +
                    "Animal4, 2008\r\n" +
                    "Animal1, 2000\r\n";

            String actualOutput = outContent.toString().trim();
            expectedOutput = expectedOutput.trim();

            assertEquals(expectedOutput, actualOutput);
        }
    }


    // Дополнительный метод для проверки, что животное старше заданного возраста
    private boolean isOlderThan(Animal animal, int age) {
        LocalDate currentDate = LocalDate.now();
        int years = currentDate.getYear() - animal.getDateOfBirth().getYear();
        return years > age;
    }


    //Создает массив животных для целей тестирования.
    private Animal[] createAnimals() {
        return new Animal[]{
                new AnimalMock("Animal1", 2000),
                new AnimalMock("Animal2", 2004),
                new AnimalMock("Animal3", 2010),
                new AnimalMock("Animal4", 2008),
                new AnimalMock("Animal5", 2005),
                new AnimalMock("Animal1", 2000),
                new AnimalMock("Animal4", 2008)
        };
    }

    //Создает массив животных(родившихся не в високосный год) для целей тестирования.
    private Animal[] createNonLeapYearAnimals() {
        return new Animal[]{
                new AnimalMock("Animal5", 2005),
                new AnimalMock("Animal6", 2007),
                new AnimalMock("Animal7", 1995),
                new AnimalMock("Animal8", 1995)
        };
    }
}