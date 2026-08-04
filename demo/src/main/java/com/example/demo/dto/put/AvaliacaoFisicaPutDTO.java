package com.example.demo.dto.put;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonFormat;

public record AvaliacaoFisicaPutDTO(

    @NotBlank(message = "O peso é obrigatório")
    @Positive(message = "O peso deve ser maior que 0")
    Double peso,

    @NotBlank(message = "A altura é obrigatória")
    @Positive(message = "A altura deve ser maior que 0")
    Double altura,

    @NotBlank(message = "A data da avaliação é obrigatória")
    @PastOrPresent(message = "A data da avaliação deve ser uma data passada ou presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataAvaliacao
) {}
