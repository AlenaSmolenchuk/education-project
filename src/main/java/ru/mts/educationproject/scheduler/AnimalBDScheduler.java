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
    private final AnimalRepository aRepository;
    private final AnimalTypeRepository animalTypeRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository, BreedRepository breedRepository, AnimalRepository aRepository, AnimalTypeRepository animalTypeRepository) {
        this.animalsRepository = animalsRepository;
        this.breedRepository = breedRepository;
        this.aRepository = aRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 20; i++) {
            breedRepository.save(
                    new Breed(String.valueOf(
                            AnimalBreeds.values()[(int) (Math.random() * AnimalBreeds.values().length)]
                    ),
                            List.of(
                                    new Animal("ANIMAL" + i,
                                            new AnimalType(
                                                    "CAT" + i, false,
                                                    List.of(new Habitat("AMERICA" + i)),
                                                    List.of(new Provider("ZOOZ" + i, "8917223123" + i))
                                            ),
                                            (short) (i % 3)
                                    )
                            )
                    )
            );
//
//            animalTypeRepository.save(new AnimalType(
//                    "WOLF" + i, false,
//                    List.of(new Habitat("ASIA" + i)),
//                    List.of(new Provider("PARK" + i, "8777111225" + i))
//            ));
//
//            aRepository.save(new Animal("RAX" + i,
//                    new AnimalType(
//                            "HAMSTER" + i, false,
//                            List.of(new Habitat("EUROPE" + i)),
//                            List.of(new Provider("LALALAND" + i, "8999221113" + i))
//                    ),
//                    (short) (i % 2)
//            ));
        }
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {
//        logger.info("Finding leap year names animals: {}", animalsRepository.findLeapYearNames());
//        logger.info("Finding older than 7 years animals: {}", animalsRepository.findOlderAnimals(7));
//        logger.info("Finding duplicate animals: {}", animalsRepository.findDuplicate());
        logger.info("Finding average age: {}", animalsRepository.findAverageAge());
    }
}
