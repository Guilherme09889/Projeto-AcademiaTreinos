package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.repository.ExercicioRepository;
import com.example.demo.model.repository.TreinoExercicioRepository;
import com.example.demo.dto.post.ExercicioCreatDTO;
import com.example.demo.dto.put.ExercicioPutDTO;
import com.example.demo.model.entity.ExercicioEntity;
import java.util.Optional;
import java.util.List;
import com.example.demo.model.Projection.ExercicioGetProjection;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private final TreinoExercicioRepository treinoExercicioRepository;

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

    @Transactional(readOnly = true)
    public List<ExercicioEntity> listarPorMusculoAlvo(String musculoAlvo){

        List<ExercicioEntity> resultadoConsulta = exercicioRepository.findByMusculoAlvoNative(musculoAlvo);

        if(resultadoConsulta.isEmpty()){
            throw new RuntimeException("Nenhum exercicio encontrado");
        }

        return resultadoConsulta;


    }

    @Transactional
    public void updateById(Long id, ExercicioPutDTO exerPutDTO){

        if(!exercicioRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercicio nao encontrado");
        }

        String nome = exerPutDTO.nome() == null ? null : exerPutDTO.nome().trim();
        String musculoAlvo = exerPutDTO.musculoAlvo() == null ? null : exerPutDTO.musculoAlvo().trim();

        if(nome != null){
            exercicioRepository.findByNomeNative(nome)
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Exercicio ja cadastrado com este nome");
                });
        }

        exercicioRepository.updateExercicioById(id, nome, musculoAlvo);
    }

    @Transactional
    public void deletarById(Long id){

        ExercicioEntity exercicio = exercicioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercicio nao encontrado"));

        if(treinoExercicioRepository.existsByExercicioId_Id(id)){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Exercicio vinculado a um ou mais treinos, remova-o dos treinos antes de deletar");
        }

        exercicioRepository.delete(exercicio);
    }

}
