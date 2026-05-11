
package com.example.demo;

public class Jogo {
    private Long id;
    private String nome;
    private String tipo;
    private Integer nota; // Removido o static e alterado para Integer
    private String review;

    public Jogo() {
    }

    public Jogo(Long id, String nome, String tipo, Integer nota, String review) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nota = nota;
        this.review = review;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getNota() { return nota; } // Nome corrigido para getNota
    public void setNota(Integer nota) { this.nota = nota; } // Nome corrigido para setNota

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}