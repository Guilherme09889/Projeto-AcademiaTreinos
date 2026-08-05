package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.TreinoExercicioEntity;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicioEntity, Long> {

    boolean existsByExercicioId_Id(Long exercicioId);

}
