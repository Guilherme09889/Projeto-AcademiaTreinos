package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;
import com.example.demo.model.entity.TreinoExercicioEntity;
import com.example.demo.dto.get.TreinoExercicioGetDTO;

@Repository
public interface TreinoExercicioRepository extends JpaRepository<TreinoExercicioEntity, Long> {

    boolean existsByExercicioId_Id(Long exercicioId);

    @NativeQuery(value = """
        select te.id as id,
        t.nome as nomeTreino,
        e.nome as nomeExercicio,
        te.series as series,
        te.repeticoes as repeticoes,
        te.carga as carga
        from treino_exercicios te
        left join treinos t on te.treino_id = t.id
        left join exercicios e on te.exercicio_id = e.exercicio_id
        order by te.id asc
    """)
    List<TreinoExercicioGetDTO> ListarAllNative();

}
