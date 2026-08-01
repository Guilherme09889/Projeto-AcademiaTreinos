package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import com.example.demo.dto.get.UsuarioGetDTO;

import com.example.demo.dto.post.UsuarioCreateDTO;
import com.example.demo.service.UsuarioService;
import java.util.List;
import com.example.demo.model.Projection.UsuarioNameCpfAvFProjection;

@RestController
@RequestMapping("/v1/usuarios")
@Validated
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody UsuarioCreateDTO x){
        usuServ.criarAluno(x);
    }

    @GetMapping
    public List<UsuarioGetDTO> listarTodosNative(@PageableDefault(size = 2, page = 0, sort = "id") Pageable pageable) {
        return usuServ.listarTodosNative(pageable);
    }

    @GetMapping("/{id}")
    public UsuarioGetDTO pegarAlunoPeloId(@PathVariable @Positive Long id) {
        return usuServ.pegarAlunoPeloId(id);
    }

    @GetMapping("/nome/{nome}")
    public List<UsuarioNameCpfAvFProjection> listarTodosPorNome(@PathVariable @NotBlank String nome) {
        return usuServ.listarTodosPorNome(nome);
    }

}
