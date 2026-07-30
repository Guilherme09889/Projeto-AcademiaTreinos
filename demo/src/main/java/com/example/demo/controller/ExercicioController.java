package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import com.example.demo.service.ExercicioService;
import com.example.demo.dto.post.ExercicioCreatDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import java.util.List;
import com.example.demo.model.Projection.ExercicioGetProjection;

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

}
