package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
@Table(name = "treino_exercicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TreinoExercicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = false)
    private TreinoEntity treinoId;

    @ManyToOne
    @JoinColumn(name = "exercicio_id", nullable = false)
    private ExercicioEntity exercicioId;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "repeticoes", nullable = false)
    private Integer repeticoes;

    @Column(name = "carga", nullable = false)
    private Double carga;
    
    
}
