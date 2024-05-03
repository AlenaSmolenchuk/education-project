package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.educationproject.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
