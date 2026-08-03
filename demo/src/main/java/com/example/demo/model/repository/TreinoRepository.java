package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.TreinoEntity;
import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<TreinoEntity, Long> {

    List<TreinoEntity> findByUsuarioIdOrderByIdAsc(Long usuarioId);

}
