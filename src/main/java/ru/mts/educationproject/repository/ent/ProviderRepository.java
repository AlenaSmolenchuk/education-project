package ru.mts.educationproject.repository.ent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.educationproject.entity.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}
