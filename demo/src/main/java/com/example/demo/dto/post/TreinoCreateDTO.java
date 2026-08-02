package com.example.demo.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreinoCreateDTO {

    @NotBlank(message = "O nome e obrigatorio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "[\\p{L}\\p{N} .'-]+", message = "O nome deve conter apenas letras, numeros, espacos e os caracteres . ' -")
    private String nome;

    @NotBlank(message = "A descricao e obrigatoria")
    @Size(min = 3, max = 255, message = "A descricao deve ter entre 3 e 255 caracteres")
    @Pattern(regexp = "[\\p{L}\\p{N} .,;:!?'\"()/-]+", message = "A descricao contem caracteres nao permitidos")
    private String descricao;

    @NotNull(message = "O usuario e obrigatorio")
    @Positive
    private Long usuarioId;

}
