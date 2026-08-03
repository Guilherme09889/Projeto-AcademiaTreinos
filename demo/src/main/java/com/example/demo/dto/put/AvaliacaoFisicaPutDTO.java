package com.example.demo.dto.put;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public record AvaliacaoFisicaPutDTO(

    @NotNull
    @Positive
    Double peso,

    @NotNull
    @Positive
    Double altura,

    @NotNull
    @PastOrPresent
    LocalDate dataAvaliacao
) {}
