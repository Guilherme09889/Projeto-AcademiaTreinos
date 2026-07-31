package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.model.Projection.UsuarioNameCpfAvFProjection;
import org.springframework.data.jpa.repository.NativeQuery;
import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByCpf(String cpf);

    @NativeQuery(value = """
    SELECT u.id AS id,
           u.nome AS nome,
           u.data_nascimento AS dataNascimento,
           u.cpf AS cpf,
           a.peso AS peso,
           a.altura AS altura
    FROM usuarios u
    LEFT JOIN avaliacoes_fisicas a ON u.avaliacao_fisica_id = a.id
    where u.nome = :nome
    ORDER BY u.id ASC
    """)
    List<UsuarioNameCpfAvFProjection> findAllNativeProjectionByNome(@Param("nome") String nome);
}
