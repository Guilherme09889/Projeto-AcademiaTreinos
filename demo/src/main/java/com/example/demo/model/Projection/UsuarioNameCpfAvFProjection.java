package com.example.demo.model.Projection;

import java.time.LocalDate;

public interface UsuarioNameCpfAvFProjection {
    Long getId();
    String getNome();
    LocalDate getDataNascimento();
    String getCpf();
    Double getPeso();
    Double getAltura();
}
