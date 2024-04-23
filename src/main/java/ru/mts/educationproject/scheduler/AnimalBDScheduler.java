package ru.mts.educationproject.scheduler;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class AnimalBDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AnimalBDScheduler.class);
    private final JdbcTemplate jdbcTemplate;

    public AnimalBDScheduler(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        logger.info("AnimalBDScheduler initialized...");

    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {
        List<Map<String, Object>> animalData = jdbcTemplate.queryForList(
                "SELECT c.name AS name, t.type AS type, c.age AS age, h.area AS area, p.name AS provider " +
                        "FROM animals.creature c " +
                        "INNER JOIN animals.animal_type t ON c.type_id = t.id_type " +
                        "INNER JOIN animals.animals_habitats ah ON c.type_id = ah.id_animal_type " +
                        "INNER JOIN animals.habitat h ON ah.area_id = h.id_area " +
                        "INNER JOIN animals.animals_provider ap ON c.type_id = ap.id_animal_type " +
                        "INNER JOIN animals.provider p ON ap.provider_id = p.id_provider"
        );

        for (Map<String, Object> row : animalData) {
            String name = (String) row.get("name");
            String type = (String) row.get("type");
            int age = (int) row.get("age");
            String area = (String) row.get("area");
            String provider = (String) row.get("provider");
            logger.info("Name: {}, Type: {}, Age: {}, Area: {}, Provider: {}", name, type, age, area, provider);
        }
    }
}
