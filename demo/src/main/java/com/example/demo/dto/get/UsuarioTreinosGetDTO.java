package com.example.demo.dto.get;

import java.time.LocalDate;

public record UsuarioTreinosGetDTO(
    String nomeUsuario,
    String nomeTreino,
    String descricaoTreino,
    LocalDate dataCriacaoTreino
) {
}
