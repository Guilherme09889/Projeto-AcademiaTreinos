package com.example.demo.dto.put;

import java.time.LocalDate;

public record UsuarioPutDTO(
    String nome,
    String cpf,
    LocalDate dataNascimento,
    String cep
) {

}
