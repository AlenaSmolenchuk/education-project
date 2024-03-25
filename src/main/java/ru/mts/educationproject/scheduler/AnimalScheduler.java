package ru.mts.educationproject.scheduler;

import org.slf4j.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.repository.AnimalsRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static ru.mts.educationproject.util.Helper.*;

@Component
public class AnimalScheduler {
    private final AnimalsRepository animalsRepository;
    private static final Logger log = LoggerFactory.getLogger(AnimalScheduler.class);
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    /**
     * Конструктор класса, принимающий на вход репозиторий животных.
     *
     * @param animalsRepository репозиторий животных.
     */
    public AnimalScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostConstruct
    public void startScheduledTasks() {
        animalsRepository.initAnimals();

        scheduler.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("PrintDuplicate");
            log.info("Thread name: {}", Thread.currentThread().getName());

            animalsRepository.printDuplicate();
        }, 0, 10, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            Thread.currentThread().setName("FindAverageAge");
            log.info("Thread name: {}", Thread.currentThread().getName());

            animalsRepository.findAverageAge();
        }, 0, 20, TimeUnit.SECONDS);
    }

    /**
     * Метод, вызываемый каждую минуту, который вызывает методы AnimalsRepository
     * и выводит результаты в стандартный вывод.
     */
    @Scheduled(fixedRate = 60000)
    public void executeScheduledTask() {
        try {
            System.out.println("\nFinding leap year names: ");
            Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
            print(leapYearNames);
            System.out.println();

            System.out.println("Finding older animals than 7 years: ");
            Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimals(7);
            print(olderAnimals);
            System.out.println();

            System.out.println("Finding the oldest and expensive animals: ");
            List<Animal> oldAndExpensive = animalsRepository.findOldAndExpensive();
            printAnimalList(oldAndExpensive);
            System.out.println();

            System.out.println("Finding min cost animals: ");
            List<String> minCostAnimals = animalsRepository.findMinCostAnimals();
            printNames(minCostAnimals);
            System.out.println();
        } catch (Exception e) {
            log.error("Something went wrong: " + e.getMessage(), e);
        }
    }
}