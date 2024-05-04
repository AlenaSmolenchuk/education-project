package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;
import ru.mts.educationproject.entity.*;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.repository.ent.AnimalRepository;
import ru.mts.educationproject.repository.ent.AnimalTypeRepository;
import ru.mts.educationproject.repository.ent.BreedRepository;
import ru.mts.educationproject.util.AnimalBreeds;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AnimalBDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AnimalBDScheduler.class);

    private final AnimalsRepository animalsRepository;
    private final BreedRepository breedRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository, BreedRepository breedRepository, AnimalRepository aRepository, AnimalTypeRepository animalTypeRepository) {
        this.animalsRepository = animalsRepository;
        this.breedRepository = breedRepository;
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {
        for (int i = 0; i < 20; i++) {
            breedRepository.save(
                    new Breed(String.valueOf(
                            AnimalBreeds.values()[(int) (Math.random() * AnimalBreeds.values().length)]
                    ),
                            List.of(
                                    new Animal("ANIMAL_" + i,
                                            new AnimalType(
                                                    "CAT_" + i, false,
                                                    List.of(new Habitat("AMERICA")),
                                                    List.of(new Provider("ZOOZ_" + i, "8917223123" + i))
                                            ),
                                            (short) (i + 1)
                                    )
                            )
                    )
            );
        }

        logger.info("Finding leap year names animals: {}", animalsRepository.findLeapYearNames());
        logger.info("Finding older than 7 years animals: {}", animalsRepository.findOlderAnimals(7));
        logger.info("Finding duplicate animals: ");
        animalsRepository.printDuplicate();
        logger.info("Finding average age: {}", animalsRepository.findAverageAge());
    }
}
