package br.com.fiap.quadro.models;

import java.time.LocalDate;

public class Quadro {
    private Long id;

    private String titulo; 
    private String colaboradores;
    private String cor;
    private LocalDate data;
    
    public Quadro(String titulo, String colaboradores, String cor, LocalDate data) {
        this.titulo = titulo;
        this.colaboradores = colaboradores;
        this.cor = cor;
        this.data = data;
    }
    
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(String colaboradores) {
        this.colaboradores = colaboradores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
