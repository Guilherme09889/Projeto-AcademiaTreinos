package com.example.demo.dto.get;

import java.time.LocalDate;

public record AvaliacaoFisicaGetDTO(
        Long id,
        Double peso,
        Double altura,
        LocalDate dataAvaliacao) {
}
