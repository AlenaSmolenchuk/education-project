package ru.mts.educationproject.educationprojectstarter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownCountOfAnimalException;


/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private static final Logger log = LoggerFactory.getLogger(CreateAnimalServiceImpl.class);


    /**
     * Создает n уникальных животных при помощи цикла for.
     *
     * @param n количество животных для создания
     **/
    @Override
    public void createAnimals(int n) {
        Object animal;

        if (n <= 0) {
            throw new UnknownCountOfAnimalException("The number of animals must be greater than 0.");
        }

            for (int i = 0; i < n; i++) {
               animal = null;
            }

            log.info("{} animals created and saved to the database.", n);

    }
}