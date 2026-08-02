package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.TreinoRepository;
import com.example.demo.dto.post.TreinoCreateDTO;
import com.example.demo.model.entity.TreinoEntity;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;

    @Transactional
    public void criarTreino(TreinoCreateDTO treinoCreateDTO) {

        TreinoEntity treino = new TreinoEntity();
        treino.setNome(treinoCreateDTO.getNome().trim());
        treino.setDescricao(treinoCreateDTO.getDescricao().trim());
        treino.setDataCriacao(LocalDate.now());

        treinoRepository.save(treino);
    }

}
