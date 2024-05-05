package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;
import ru.mts.educationproject.entity.*;
import ru.mts.educationproject.repository.AnimalsRepository;
import ru.mts.educationproject.repository.ent.AnimalRepository;
import ru.mts.educationproject.repository.ent.AnimalTypeRepository;
import ru.mts.educationproject.repository.ent.BreedRepository;

import java.util.List;

@Component
public class AnimalBDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AnimalBDScheduler.class);

    private final AnimalsRepository animalsRepository;
    private final BreedRepository breedRepository;

    private final AnimalRepository animalRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository, BreedRepository breedRepository, AnimalRepository aRepository, AnimalTypeRepository animalTypeRepository, AnimalRepository animalRepository) {
        this.animalsRepository = animalsRepository;
        this.breedRepository = breedRepository;
        this.animalRepository = animalRepository;
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {

//        logger.info("Finding leap year names animals: {}", animalsRepository.findLeapYearNames());
//        logger.info("Finding older than 7 years animals: {}", animalsRepository.findOlderAnimals(7));
//        logger.info("Finding duplicate animals: ");
//        animalsRepository.printDuplicate();
        logger.info("Finding average age: {}", animalRepository.findByAgeGreaterThan(7));
    }
}
