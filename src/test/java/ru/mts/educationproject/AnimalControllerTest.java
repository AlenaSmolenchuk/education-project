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
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.entity.AnimalType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        Animal animal = new Animal();
        animal.setName("TestAnimal");
        animal.setType(new AnimalType("Some type"));
        animal.setAge((short) 2);

        mockMvc.perform(
                        post("/new")
                                .param("action", "CREATE")
                                .flashAttr("animal", animal)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    void deleteAnimalTest() throws Exception {
        int id = 10;

        mockMvc.perform(
                        get("/delete/{id}", id)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    void addNewAnimalTest() throws Exception {
        long beforeCall = jdbcTemplate.queryForObject("select count(*) from animals.animal", Long.class);
        System.out.println("beforeCall = "  + beforeCall);

        Animal animal = new Animal();
        animal.setName("TestAnimal");
        animal.setType(new AnimalType("Some type"));
        animal.setAge((short) 2);

        mockMvc.perform(
                        post("/new")
                                .param("action", "CREATE")
                                .flashAttr("animal", animal)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        long afterCall = jdbcTemplate.queryForObject("select count(*) from animals.animal", Long.class);
        System.out.println("afterCall = " + afterCall);

        assertThat(afterCall, IsEqual.equalTo(beforeCall + 1));

    }
}
