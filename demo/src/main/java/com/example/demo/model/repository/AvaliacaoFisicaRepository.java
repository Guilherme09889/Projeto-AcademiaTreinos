package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.entity.AvaliacaoFisicaEntity;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisicaEntity, Long> {

}
