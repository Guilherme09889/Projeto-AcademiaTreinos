package com.example.demo.dto.get;

public record TreinoExercicioGetDTO(
    Long id,
    String nomeTreino,
    String nomeExercicio,
    Integer series,
    Integer repeticoes,
    Double carga
){}
