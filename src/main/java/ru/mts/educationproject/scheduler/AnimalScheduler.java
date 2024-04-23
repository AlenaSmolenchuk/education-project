//package ru.mts.educationproject.scheduler;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import org.slf4j.*;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
//import ru.mts.educationproject.exception.FileException;
//import ru.mts.educationproject.repository.AnimalsRepository;
//import ru.mts.educationproject.util.Constants;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class AnimalScheduler {
//    private final AnimalsRepository animalsRepository;
//    private static final Logger log = LoggerFactory.getLogger(AnimalScheduler.class);
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
//
//    /**
//     * Конструктор класса, принимающий на вход репозиторий животных.
//     *
//     * @param animalsRepository репозиторий животных.
//     */
//    public AnimalScheduler(AnimalsRepository animalsRepository) {
//        this.animalsRepository = animalsRepository;
//    }
//
//    @PostConstruct
//    public void startScheduledTasks() {
//        animalsRepository.initAnimals();
//
//        scheduler.scheduleAtFixedRate(() -> {
//            animalsRepository.findDuplicate();
//            Thread.currentThread().setName("PrintDuplicate");
//            try {
//                log.info("Thread name: {}", Thread.currentThread().getName());
//                Map<String, List<Animal>> duplicates = animalsRepository.readJson(Constants.FIND_DUPLICATE
//                        , new TypeReference<>() {});
//
//                log.info("Found animals: {}", duplicates);
//            } catch (IOException e) {
//                throw new FileException("Failed to read a file" + e.getMessage() + e);
//            }
//        }, 0, 10, TimeUnit.SECONDS);
//
//        scheduler.scheduleAtFixedRate(() -> {
//            animalsRepository.findAverageAge();
//            Thread.currentThread().setName("FindAverageAge");
//            try {
//                log.info("Thread name: {}", Thread.currentThread().getName());
//                Double averageAge = animalsRepository.readJson(Constants.FIND_AVERAGE_AGE, new TypeReference<>() {});
//                log.info("Average age of animals: {}", averageAge);
//            } catch (IOException e) {
//                throw new FileException("Failed to read a file" + e.getMessage() + e);
//            }
//        }, 0, 20, TimeUnit.SECONDS);
//    }
//
//    /**
//     * Метод, вызываемый каждую минуту, который вызывает методы AnimalsRepository
//     * и выводит результаты в стандартный вывод.
//     */
//    @Scheduled(fixedRate = 60000)
//    public void executeScheduledTask() {
//        try {
//            log.info("Finding leap year names: ");
//            animalsRepository.findLeapYearNames();
//            Map<String, LocalDate> leapYearNames = animalsRepository.readJson(Constants.FIND_LEAP_YEAR_NAMES,
//                    new TypeReference<>() {});
//            log.info("Found animals: {}", leapYearNames);
//
//            log.info("Finding older animals than 7 years: ");
//            animalsRepository.findOlderAnimals(7);
//            Map<String, Integer> olderAnimals = animalsRepository.readJson(Constants.FIND_OLDER_ANIMALS,
//                    new TypeReference<>() {});
//            log.info("Found animals: {}", olderAnimals);
//
//            log.info("Finding the oldest and expensive animals: ");
//            animalsRepository.findOldAndExpensive();
//            List<Animal> oldAndExpensiveAnimals = animalsRepository.readJson(Constants.FIND_OLD_AND_EXPENSIVE,
//                    new TypeReference<>(){});
//            log.info("Found animals: {}", oldAndExpensiveAnimals);
//
//            log.info("Finding min cost animals: ");
//            animalsRepository.findMinCostAnimals();
//            List<String> minCostAnimals = animalsRepository.readJson(Constants.FIND_MIN_COST_ANIMALS,
//                    new TypeReference<>() {});
//            log.info("Found animals: {}", minCostAnimals);
//
//        } catch (Exception e) {
//            log.error("Something went wrong: " + e.getMessage(), e);
//        }
//    }
//}