package com.example.demo.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExercicioCreatDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    @Pattern(regexp = "[\\p{L}\\p{N} .'-]+", message = "O nome deve conter apenas letras, numeros, espacos e os caracteres . ' -")
    private String nome;

    @NotBlank
    @Size(min = 3, max = 60)
    @Pattern(regexp = "[\\p{L} '-]+", message = "O musculo alvo deve conter apenas letras, espacos e os caracteres ' -")
    private String musculoAlvo;

}
