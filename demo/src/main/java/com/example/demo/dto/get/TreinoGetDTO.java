package com.example.demo.dto.get;

import java.time.LocalDate;

public record TreinoGetDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate dataCriacao
) {

}
