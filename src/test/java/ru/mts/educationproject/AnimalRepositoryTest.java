package ru.mts.educationproject;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mts.educationproject.aop.LoggingAspect;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.repository.AnimalsRepositoryImpl;
import ru.mts.educationproject.repository.dao.AnimalRepository;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AnimalRepositoryTest {

    @MockBean
    private AnimalsRepository animalsRepository;
    @MockBean
    private AnimalRepository animalRepository;
    @InjectMocks
    private ObjectMapper objectMapper;
    private ListAppender<ILoggingEvent> listAppender;
    Logger logger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);

    @BeforeEach
    void setUp() {
        animalRepository = mock(AnimalRepository.class);
        animalsRepository = new AnimalsRepositoryImpl(objectMapper, animalRepository);

        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        logger.detachAppender(listAppender);
    }

    @Test
    void testFindOlderAnimals() throws JsonProcessingException {
        Animal animal = new Animal();
        animal.setAge((short) 9);
        when(animalRepository.findByAgeGreaterThanEqual((short) 10)).thenReturn(Collections.emptyList());
        when(animalRepository.findAll()).thenReturn(Collections.singletonList(animal));

        Map<Animal, Integer> result = animalsRepository.findOlderAnimals((short) 10);

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly(
                        ">> Enter findOlderAnimals: Find older animals",
                        ">> findOlderAnimals parameters: [10]",
                        ">> Enter findOldest: Find oldest",
                        "<< Exit findOldest: Find oldest",
                        "<< findOlderAnimals result: " + objectMapper.writeValueAsString(result),
                        "<< Exit findOlderAnimals: Find older animals"
                );
    }
}
