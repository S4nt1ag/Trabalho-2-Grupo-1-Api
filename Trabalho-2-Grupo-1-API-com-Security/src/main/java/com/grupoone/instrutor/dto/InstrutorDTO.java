package com.grupoone.instrutor.dto;

public class InstrutorDTO {

    private String nome;

    public InstrutorDTO() {

    }

    public InstrutorDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Instrutor = " + nome + "]";
    }
}