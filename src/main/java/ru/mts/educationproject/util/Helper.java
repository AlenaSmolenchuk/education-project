package ru.mts.educationproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import ru.mts.educationproject.educationprojectstarter.exceptionst.EmptyFileStarterException;
import ru.mts.educationproject.educationprojectstarter.utilstarter.ConstantsStarter;
import ru.mts.educationproject.entity.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Helper {

    private static final Logger log = LoggerFactory.getLogger(Helper.class);

    // Вспомогательный метод для нахождения самого взрослого животного
    public static Animal findOldest(List<Animal> animalList) {
        return animalList.stream()
                .max(Comparator.comparingInt(Animal::getAge))
                .orElse(null);
    }
    public static String createSecret() {
        try {
            List<String> secretInfo = Files
                    .readAllLines(ResourceUtils.getFile(ConstantsStarter.SECRET_INFORMATION).toPath());
            if (!secretInfo.isEmpty()) {
                Random rand = new Random();
                int index = rand.nextInt(secretInfo.size());

                return secretInfo.get(index);
            } else {
                throw new EmptyFileStarterException("This file is empty");
            }

        } catch (IOException e) {
            log.error("Something went wrong by by reading data: " + e.getMessage(), e);
            return "Failed to read secret information";
        }
    }

    public static String animalToString(Animal animal, int currentCounter) {
        return String.format("%d %s %s %s %s %s",
                currentCounter,
                animal.getType(),
                animal.getBreed(),
                animal.getName(),
                animal.getAge());
    }
}
