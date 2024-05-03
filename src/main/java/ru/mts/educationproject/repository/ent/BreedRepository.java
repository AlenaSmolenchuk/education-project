package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.educationproject.entity.Breed;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
}
