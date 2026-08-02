package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.ExercicioEntity;
import java.util.List;
import java.util.Optional;
import com.example.demo.model.Projection.ExercicioGetProjection;

@Repository
public interface ExercicioRepository extends JpaRepository<ExercicioEntity, Long> {

    @NativeQuery(value = "SELECT * FROM exercicios WHERE nome = :nome")
    Optional<ExercicioEntity> findByNomeNative(@Param("nome") String nome);

    @NativeQuery(value = """
    SELECT exercicio_id AS id,
           nome AS nome,
           musculo_alvo AS musculoAlvo
    FROM exercicios
    ORDER BY exercicio_id ASC
    """)
    List<ExercicioGetProjection> findAllNativeProjection();

    @NativeQuery(value = "SELECT * FROM exercicios WHERE musculo_alvo LIKE CONCAT(:musculoAlvo, '%')")
    List<ExercicioEntity> findByMusculoAlvoNative(@Param("musculoAlvo") String musculoAlvo);

}
