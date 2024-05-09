package ru.mts.educationproject.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;
import ru.mts.educationproject.repository.AnimalsRepository;

@Component
public class AnimalBDScheduler {
    private static final Logger logger = LoggerFactory.getLogger(AnimalBDScheduler.class);

    private final AnimalsRepository animalsRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {

        logger.info("Finding leap year names animals: {}", animalsRepository.findLeapYearNames());
        logger.info("Finding older than 8 years animals: {}", animalsRepository.findOlderAnimals((short) 8));
        logger.info("Finding duplicate animals: ");
        animalsRepository.printDuplicate();
        logger.info("Finding average age: {}", animalsRepository.findAverageAge());
    }
}
