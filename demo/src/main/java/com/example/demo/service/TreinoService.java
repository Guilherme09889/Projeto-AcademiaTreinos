package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.TreinoRepository;
import com.example.demo.model.repository.UsuarioRepository;
import com.example.demo.dto.post.TreinoCreateDTO;
import com.example.demo.model.entity.TreinoEntity;
import com.example.demo.model.entity.UsuarioEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import com.example.demo.dto.get.UsuarioTreinosGetDTO;
import java.util.List;
import com.example.demo.dto.get.TreinoGetDTO;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void criarTreino(TreinoCreateDTO treinoCreateDTO) {

        UsuarioEntity usuario = usuarioRepository.findById(treinoCreateDTO.getUsuarioId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        TreinoEntity treino = new TreinoEntity();
        treino.setUsuario(usuario);
        treino.setNome(treinoCreateDTO.getNome().trim());
        treino.setDescricao(treinoCreateDTO.getDescricao().trim());
        treino.setDataCriacao(LocalDate.now());

        treinoRepository.save(treino);
    }

    @Transactional(readOnly = true)
    public UsuarioTreinosGetDTO getTreinoByUsuarioId(Long usuarioId) {

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        List<TreinoGetDTO> treinos = treinoRepository.findByUsuarioIdOrderByIdAsc(usuarioId).stream()
            .map(t -> new TreinoGetDTO(t.getId(), t.getNome(), t.getDescricao(), t.getDataCriacao()))
            .toList();

        return new UsuarioTreinosGetDTO(usuario.getNome(), treinos);
    }
}
