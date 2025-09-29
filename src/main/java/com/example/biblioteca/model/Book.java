package com.example.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    private String titulo;

    @NotBlank(message = "O nome do autor é obrigatório!")
    private String autor;

    private String genero;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private boolean lido;

    @DecimalMin(value = "0.0", message = "A nota mínima é 0")
    @DecimalMax(value = "5.0", message = "A nota máxima é 5")
    private double nota = 0.0;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isLido() {
        return lido;
    }
    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
}