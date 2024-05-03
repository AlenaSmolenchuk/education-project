package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;
import ru.mts.educationproject.entity.*;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.repository.ent.BreedRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AnimalBDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AnimalBDScheduler.class);

    private final AnimalsRepository animalsRepository;
    private final BreedRepository breedRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository, BreedRepository breedRepository) {
        this.animalsRepository = animalsRepository;
        this.breedRepository = breedRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            breedRepository.save(
                    new Breed("BLACK" + i,
                            List.of(
                                    new Animal("ANIMAL" + i,
                                            new AnimalType(
                                                    "CAT" + i, true,
                                                    List.of(new Habitat("AMERICA" + i)),
                                                    List.of(new Provider("ZOOZ" + i, "89172231232" + i))
                                            ),
                                            (short) (i % 3)
                                    )
                            )
                    )
            );
        }
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {
        logger.info("Finding leap year names animals: {}", animalsRepository.findLeapYearNames());
        logger.info("Finding older than 7 years animals: {}", animalsRepository.findOlderAnimals(7));
        logger.info("Finding duplicate animals: {}", animalsRepository.findDuplicate());
        logger.info("Finding average age: {}", animalsRepository.findAverageAge());
    }
}
