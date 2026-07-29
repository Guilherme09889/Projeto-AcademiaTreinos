package com.example.demo.dto.get;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioGetDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        LocalDateTime criadoEm) {
}
