package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.educationproject.entity.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByAgeGreaterThan(int age);
}
