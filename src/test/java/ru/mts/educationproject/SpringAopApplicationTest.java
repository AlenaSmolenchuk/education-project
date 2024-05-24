package ru.mts.educationproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.entity.AnimalType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringAopApplicationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void create() throws Exception {
        Animal animal = new Animal();
        animal.setName("TestAnimal");
        animal.setType(new AnimalType("Some type"));
        animal.setAge((short) 2);

        mockMvc.perform(
                        post("/new")
                                .param("action", "CREATE")
                                .flashAttr("animal", animal)
                );
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(
                get("/index")
        );
    }

    @Test
    void delete() throws Exception {
        int id = 1;

        mockMvc.perform(
                        get("/delete/{id}", id)
                );
    }
}
