package com.example.demo.dto.put;

import jakarta.validation.constraints.Size;

public record ExercicioPutDTO(
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    String nome,

    @Size(min = 3, max = 60, message = "O musculo alvo deve ter entre 3 e 60 caracteres")
    String musculoAlvo
) {}
