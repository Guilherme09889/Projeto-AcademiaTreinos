package com.example.demo.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.TreinoExercicioRepository;
import com.example.demo.model.repository.TreinoRepository;
import com.example.demo.model.repository.ExercicioRepository;
import com.example.demo.dto.post.TreinoExercicioCreateDTO;
import com.example.demo.model.entity.TreinoEntity;
import com.example.demo.model.entity.ExercicioEntity;
import com.example.demo.model.entity.TreinoExercicioEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import com.example.demo.dto.get.TreinoExercicioGetDTO;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreinoExercicioService {

    private final TreinoExercicioRepository treinoExercicioRepository;
    private final TreinoRepository treinoRepository;
    private final ExercicioRepository exercicioRepository;

    @Transactional
    public void criarTreinoExercicio(TreinoExercicioCreateDTO treinoExerCreateDTO) {

        TreinoEntity treino = treinoRepository.findById(treinoExerCreateDTO.getTreinoId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Treino nao encontrado"));

        ExercicioEntity exer = exercicioRepository.findById(treinoExerCreateDTO.getExercicioId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercicio nao encontrado"));

        TreinoExercicioEntity treinoExer = new TreinoExercicioEntity();
        treinoExer.setTreinoId(treino);
        treinoExer.setExercicioId(exer);
        treinoExer.setSeries(treinoExerCreateDTO.getSeries());
        treinoExer.setRepeticoes(treinoExerCreateDTO.getRepeticoes());
        treinoExer.setCarga(treinoExerCreateDTO.getCarga());

        treinoExercicioRepository.save(treinoExer);
    }

    @Transactional(readOnly = true)
    public List<TreinoExercicioGetDTO> listarAllNative() {
        List<TreinoExercicioGetDTO> treinoExer = treinoExercicioRepository.ListarAllNative();
        if (treinoExer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TreinoExercicio nao encontrado");
        }
        return treinoExer;
    }

}
