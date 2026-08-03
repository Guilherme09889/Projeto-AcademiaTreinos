package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import com.example.demo.model.entity.AvaliacaoFisicaEntity;
import java.time.LocalDate;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisicaEntity, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @NativeQuery(value = """
        update avaliacoes_fisicas a
        set a.peso = :peso,
            a.altura = :altura,
            a.data_avaliacao = :dataAvaliacao
        where a.id = :id
        """)
    int updateAvaliacaoFisicaById(@Param("id") Long id,
                                  @Param("peso") Double peso,
                                  @Param("altura") Double altura,
                                  @Param("dataAvaliacao") LocalDate dataAvaliacao);

}
