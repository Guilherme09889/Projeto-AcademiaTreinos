package com.example.demo.model.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.entity.UsuarioEntity;
import com.example.demo.dto.get.UsuarioGetDTO;
import com.example.demo.model.Projection.UsuarioNameCpfAvFProjection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import com.example.demo.dto.get.UsuarioTreinosGetDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByCpf(String cpf);

    @NativeQuery(value = """
        select u.id,
        u.nome as nome,
        u.data_nascimento as dataNascimento,
        Date(u.criado_em) as criadoEm
        from usuarios u
        order by u.id asc
    """,
                countQuery = """
                select count(*) from usuarios
                """)
    List<UsuarioGetDTO> findAllNative(Pageable pageable);

    @NativeQuery(value = """
    SELECT u.id AS id,
           u.nome AS nome,
           u.data_nascimento AS dataNascimento,
           u.cpf AS cpf,
           a.peso AS peso,
           a.altura AS altura
    FROM usuarios u
    LEFT JOIN avaliacoes_fisicas a ON u.avaliacao_fisica_id = a.id
    where u.nome like :nome%
    ORDER BY u.id ASC
    """)
    List<UsuarioNameCpfAvFProjection> findAllNativeProjectionByNome(@Param("nome") String nome);


    @NativeQuery(value = """
        select u.nome as nomeUsuario,
            t.nome as nomeTreino,
            t.descricao as descricaoTreino,
            t.data_criacao as dataCriacaoTreino
        from usuarios u
        left join treinos t on u.id = t.usuario_id
        where u.id = :usuarioId
        order by t.id asc
        """)
    List<UsuarioTreinosGetDTO> findAllNativeProjectionByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @NativeQuery(value = """
        update usuarios u
        set u.nome = coalesce(:nome, u.nome),
            u.cpf = coalesce(:cpf, u.cpf),
            u.data_nascimento = coalesce(:dataNascimento, u.data_nascimento),
            u.cep = coalesce(:cep, u.cep)
        where u.id = :id
        """)
    int updateUsuarioById(@Param("id") Long id,
                          @Param("nome") String nome,
                          @Param("cpf") String cpf,
                          @Param("dataNascimento") LocalDate dataNascimento,
                          @Param("cep") String cep);
}