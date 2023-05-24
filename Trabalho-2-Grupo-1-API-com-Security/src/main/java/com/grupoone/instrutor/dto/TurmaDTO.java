package com.grupoone.instrutor.dto;

public class TurmaDTO {

    
    private String nomeDisciplina;
    private String diaSemana;
    private InstrutorDTO instrutorDto;
    
    public TurmaDTO() {
        instrutorDto = new InstrutorDTO();
    }

    public TurmaDTO(String nomeDisciplina, String diaSemana, InstrutorDTO instrutorDto) {
        this.nomeDisciplina = nomeDisciplina;
        this.diaSemana = diaSemana;
        this.instrutorDto = instrutorDto;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public InstrutorDTO getInstrutorDto() {
        return instrutorDto;
    }

    public void setInstrutorDto(InstrutorDTO instrutorDto) {
        this.instrutorDto = instrutorDto;
    }

    @Override
    public String toString() {
        return "Nome Disciplina = " + nomeDisciplina 
                + ", Dia da Semana = " + diaSemana
                + instrutorDto + "]";
    }    
}