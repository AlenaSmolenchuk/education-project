package ru.mts.educationproject.repository;

import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.util.Set;

/**
 * Объявление интерфейса, представляющий репозиторий для работы с животными.
 */
public interface AnimalsRepository {

    /**
     * Возвращает массив имен животных, рожденных в високосные годы.
     *
     * @return массив строк с именами животных, рожденных в високосные годы.
     */
    String[] findLeapYearNames();

    /**
     * Возвращает массив животных, старше заданного возраста.
     *
     * @param age возраст, по отношению к которому производится поиск.
     * @return массив животных, старше заданного возраста.
     */
    Animal[] findOlderAnimal(int age);

    /**
     * Находит и возвращает множество дубликатов животных в репозитории.
     *
     * @return множество дубликатов животных.
     */
    Set<Animal> findDuplicate();

    /**
     * Выводит в консоль информацию о дубликатах животных в репозитории.
     */
    void printDuplicate();

}