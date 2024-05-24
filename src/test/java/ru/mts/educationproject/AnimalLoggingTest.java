package ru.mts.educationproject;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.mts.educationproject.aop.LoggingAspect;
import ru.mts.educationproject.controller.AnimalController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import ru.mts.educationproject.service.AnimalService;

@SpringBootTest
public class AnimalLoggingTest {
    private ListAppender<ILoggingEvent> listAppender;

    @Autowired
    private AnimalController animalController;

    @MockBean
    private AnimalService animalService;

    private MockMvc mockMvc;

    Logger logger = (Logger) LoggerFactory.getLogger(LoggingAspect.class);

    @BeforeEach
    public void setUp() {
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();
    }

    @AfterEach
    public void tearDown() {
        logger.detachAppender(listAppender);
    }

    @Test
    public void testDeleteLogging() throws Exception {
        Mockito.doNothing().when(animalService).delete(Mockito.anyInt());
        int id = 1;

        mockMvc.perform(get("/delete/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        assertThat(listAppender.list)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly(
                        ">> Enter delete: Deleting an animal",
                        ">> delete parameters: [1]",
                        "<< delete result: \"redirect:/index\"",
                        "<< Exit delete: Deleting an animal"
                );
    }
}
