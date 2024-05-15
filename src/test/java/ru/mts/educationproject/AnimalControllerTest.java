package ru.mts.educationproject;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void createNewAnimalTest() throws Exception {
        mockMvc.perform(
                post("/animals/api/new")
                        .content(
                                "{\n" +
                                        "    \"idAnimal\": 75,\n" +
                                        "    \"name\": \"TestAnimal\",\n" +
                                        "    \"type\": \"Some type\",\n" +
                                        "    \"breed\": \"Some breed\",\n" +
                                        "    \"age\":22\n" +
                                        "}")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(content().string("SUCCESSFULLY ADDED"));
    }

    @Test
    void deleteAnimalTest() throws Exception {
        mockMvc.perform(
                        post("/animals/api/delete")
                                .content(
                                        "{\n" +
                                                "    \"idAnimal\": 75,\n" +
                                                "    \"name\": \"TestAnimal\",\n" +
                                                "    \"type\": \"Some type\",\n" +
                                                "    \"breed\": \"Some breed\",\n" +
                                                "    \"age\":22\n" +
                                                "}")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(content().string("SUCCESSFULLY DELETED"));
    }

    @Test
    void addNewAnimalTest() throws Exception {
        long beforeCall = jdbcTemplate.queryForObject("select count(*) from animals.animal", Long.class);
        System.out.println("beforeCall = "  + beforeCall);
        mockMvc.perform(
                        post("/animals/api/new")
                                .content(
                                        "{\n" +
                                                "    \"idAnimal\": 75,\n" +
                                                "    \"name\": \"TestAnimal\",\n" +
                                                "    \"type\": \"Some type\",\n" +
                                                "    \"breed\": \"Some breed\",\n" +
                                                "    \"age\":22\n" +
                                                "}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESSFULLY ADDED"));
        long afterCall = jdbcTemplate.queryForObject("select count(*) from animals.animal", Long.class);
        System.out.println("afterCall = " + afterCall);
        assertThat(afterCall, IsEqual.equalTo(beforeCall + 1));

    }
}
