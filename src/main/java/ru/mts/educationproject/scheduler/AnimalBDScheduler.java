package ru.mts.educationproject.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.*;
import ru.mts.educationproject.repository.AnimalsRepository;

@Log4j2
@Component
public class AnimalBDScheduler {

    private final AnimalsRepository animalsRepository;

    public AnimalBDScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 70000)
    public void fetchAnimalsFromDatabase() {

        animalsRepository.findLeapYearNames();
        animalsRepository.findOlderAnimals((short) 38);
        animalsRepository.printDuplicate();
        animalsRepository.findAverageAge();
    }
}
