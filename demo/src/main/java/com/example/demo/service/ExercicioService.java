package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.ExercicioRepository;
import com.example.demo.dto.post.ExercicioCreatDTO;
import com.example.demo.model.entity.ExercicioEntity;
import java.util.Optional;
import java.util.List;
import com.example.demo.model.Projection.ExercicioGetProjection;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    @Transactional
    public void criarExercicio(ExercicioCreatDTO exerCreateDTO){

        Optional<ExercicioEntity> resultadoConsulta = exercicioRepository.findByNomeNative(exerCreateDTO.getNome());

        if(resultadoConsulta.isPresent()){
            throw new RuntimeException("Exercicio ja cadastrado");
        }

        ExercicioEntity exer = new ExercicioEntity();
        exer.setNome(exerCreateDTO.getNome());
        exer.setMusculoAlvo(exerCreateDTO.getMusculoAlvo());
        exercicioRepository.save(exer);

    }

    @Transactional(readOnly = true)
    public List<ExercicioGetProjection> listarTodos(){
        return exercicioRepository.findAllNativeProjection();
    }

}
