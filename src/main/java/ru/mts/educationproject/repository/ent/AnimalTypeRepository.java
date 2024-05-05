package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.educationproject.entity.AnimalType;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
}
