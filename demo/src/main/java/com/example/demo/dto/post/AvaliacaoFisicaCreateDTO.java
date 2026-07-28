package com.example.demo.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AvaliacaoFisicaCreateDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    @Positive
    private Double peso;

    @NotNull
    @Positive
    private Double altura;
}
