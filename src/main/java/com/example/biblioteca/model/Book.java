package com.example.biblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    private String titulo;

    @NotBlank(message = "O nome do autor é obrigatório!")
    private String autor;

    private String genero;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean terminado;

    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 5, message = "A nota máxima é 5")
    private int nota = 0;

    @NotBlank(message = "O status de leitura é obrigatório")
    private String statusLeitura;

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
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    public boolean isLido() {
        return terminado;
    }
    public void setLido(boolean lido) {
        this.terminado = lido;
    }
}
