package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.educationproject.entity.AnimalType;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
}
