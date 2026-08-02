package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import com.example.demo.service.TreinoService;
import com.example.demo.dto.post.TreinoCreateDTO;


@RestController
@RequestMapping("/treinos")
@RequiredArgsConstructor
@Validated
public class TreinoController {

    private final TreinoService treiServ;

    @PostMapping
    public void criarTreino(@Valid @RequestBody TreinoCreateDTO x) {
        treiServ.criarTreino(x);
    }

}
