package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import com.example.demo.service.TreinoService;
import com.example.demo.dto.post.TreinoCreateDTO;
import com.example.demo.dto.get.UsuarioTreinosGetDTO;


@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinoController {

    private final TreinoService treiServ;

    @PostMapping
    public void criarTreino(@Valid @RequestBody TreinoCreateDTO x) {
        treiServ.criarTreino(x);
    }

    @GetMapping("/usuario/{usuarioId}")
    public UsuarioTreinosGetDTO getTreinoByUsuarioId(@PathVariable @Positive Long usuarioId) {
        return treiServ.getTreinoByUsuarioId(usuarioId);
    }

}
