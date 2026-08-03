package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import com.example.demo.service.AvaliacaoFisicaService;
import com.example.demo.dto.post.AvaliacaoFisicaCreateDTO;
import com.example.demo.dto.get.AvaliacaoFisicaGetDTO;
import com.example.demo.dto.put.AvaliacaoFisicaPutDTO;

@RestController
@RequestMapping("/v1/avaliacao-fisica")
@RequiredArgsConstructor
@Validated
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaService avaFisServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaCreateDTO x){
        avaFisServ.criarAvaliacaoFisica(x);
    }

    @GetMapping("/usuario/{usuarioId}")
    public AvaliacaoFisicaGetDTO buscarAvaliacaoFisca(@PathVariable @Positive Long usuarioId){
        return avaFisServ.buscarAvaliacaoFisica(usuarioId);
    }

    @PutMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAvaliacaoFisica(@PathVariable @Positive Long usuarioId,
                                      @Valid @RequestBody AvaliacaoFisicaPutDTO x){
        avaFisServ.updateAvaliacaoFisica(usuarioId, x);
    }

    @DeleteMapping("/usuario/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAvaliacaoFisica(@PathVariable @Positive Long usuarioId){
        avaFisServ.deletarAvaliacaoFisica(usuarioId);
    }

}
