package ru.mts.educationproject.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.educationproject.entity.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
}