package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.entity.ExercicioEntity;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, Long> {

}
