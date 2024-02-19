package ru.mts.educationproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "animal")
public class TestAnimalProperties {

    private final Map<String, AnimalTypeProperties> types = new HashMap<>();

    public Map<String, AnimalTypeProperties> getTypes() {
        return types;
    }

    public static class AnimalTypeProperties {

        private String[] names;

        public String[] getNames() {
            return names;
        }

        public void setNames(String[] names) {
            this.names = names;
        }
    }
}
