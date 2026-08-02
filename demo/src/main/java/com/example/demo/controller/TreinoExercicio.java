package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.example.demo.service.TreinoExercicioService;
import com.example.demo.dto.post.TreinoExercicioCreateDTO;


@RestController
@RequestMapping("/v1/treino-exercicios")
@RequiredArgsConstructor
@Validated
public class TreinoExercicio {

    private final TreinoExercicioService treiExerServ;

    @PostMapping
    public void criarTreinoExercicio(@Valid @RequestBody TreinoExercicioCreateDTO x) {
        treiExerServ.criarTreinoExercicio(x);
    }

}
