package com.example.demo.dto.get;

import java.util.List;
public record UsuarioTreinosGetDTO(
    String nomeUsuario,
    List<TreinoGetDTO> treinos
) {
}
