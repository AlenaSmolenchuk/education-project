package ru.mts.educationproject.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.educationproject.entity.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByAgeGreaterThanEqual(short age);

    void deleteByIdAnimal(int id);
}
