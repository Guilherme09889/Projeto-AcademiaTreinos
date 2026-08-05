package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import com.example.demo.service.ExercicioService;
import com.example.demo.dto.post.ExercicioCreatDTO;
import com.example.demo.dto.put.ExercicioPutDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import com.example.demo.model.Projection.ExercicioGetProjection;
import com.example.demo.model.entity.ExercicioEntity;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExercicioController {

    private final ExercicioService exerServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarExercicio(@Valid @RequestBody ExercicioCreatDTO exerCreateDTO){
        exerServ.criarExercicio(exerCreateDTO);
    }

    @GetMapping
    public List<ExercicioGetProjection> listarTodos() {
        return exerServ.listarTodos();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/musculo-alvo/{musculoAlvo}")
    public List<ExercicioEntity> listarPorMusculoAlvo(@Valid @PathVariable String musculoAlvo){
        return exerServ.listarPorMusculoAlvo(musculoAlvo);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable @Positive Long id, @Valid @RequestBody ExercicioPutDTO exerPutDTO){
        exerServ.updateById(id, exerPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarById(@PathVariable @Positive Long id){
        exerServ.deletarById(id);
    }

}
