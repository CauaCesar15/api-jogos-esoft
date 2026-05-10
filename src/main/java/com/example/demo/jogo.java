
package com.example.demo;

public class jogo {

    private Long id;
    private String nome;
    private String tipo;
    private int nota;
    private String review;

    public jogo() {
    }

    public jogo(Long id, String nome, String tipo, int nota, String
            review) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nota = nota;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

