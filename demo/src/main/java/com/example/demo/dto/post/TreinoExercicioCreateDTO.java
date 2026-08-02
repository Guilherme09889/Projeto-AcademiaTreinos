package com.example.demo.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreinoExercicioCreateDTO {

    @NotNull(message = "O treino e obrigatorio")
    @Positive(message = "O id do treino deve ser positivo")
    private Long treinoId;

    @NotNull(message = "O exercicio e obrigatorio")
    @Positive(message = "O id do exercicio deve ser positivo")
    private Long exercicioId;

    @NotNull(message = "As series sao obrigatorias")
    @Positive(message = "As series devem ser maiores que zero")
    @Max(value = 50, message = "As series devem ser no maximo 50")
    private Integer series;

    @NotNull(message = "As repeticoes sao obrigatorias")
    @Positive(message = "As repeticoes devem ser maiores que zero")
    @Max(value = 500, message = "As repeticoes devem ser no maximo 500")
    private Integer repeticoes;

    // Zero e permitido para exercicios feitos apenas com o peso do corpo
    @NotNull(message = "A carga e obrigatoria")
    @PositiveOrZero(message = "A carga nao pode ser negativa")
    @DecimalMax(value = "1000.0", message = "A carga deve ser no maximo 1000 kg")
    @Digits(integer = 4, fraction = 2, message = "A carga deve ter no maximo 2 casas decimais")
    private Double carga;

}
