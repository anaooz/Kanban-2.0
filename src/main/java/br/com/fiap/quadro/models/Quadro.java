package br.com.fiap.quadro.models;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quadro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String titulo;

    private String colaboradores;

    @NotBlank
    private String cor;

    @NotNull
    private LocalDate data;

    @ManyToOne
    private Usuario usuario;
}