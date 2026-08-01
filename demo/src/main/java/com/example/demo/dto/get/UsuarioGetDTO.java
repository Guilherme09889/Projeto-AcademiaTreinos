package com.example.demo.dto.get;

import java.time.LocalDate;

public record UsuarioGetDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        LocalDate criadoEm) {
}
