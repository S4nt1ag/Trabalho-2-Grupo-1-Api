package com.grupoone.instrutor.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTelefone")
@Entity
@Table(name = "telefone")
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_telefone")
	private Integer idTelefone;

	@NotBlank
	@Pattern(regexp = "^[0-9]{8,15}")
	@Column(name = "numero", unique = true)
	private String numero;

	@OneToOne // (1,1)
	@JoinColumn(name = "id_instrutor", referencedColumnName = "id_instrutor") // FK--> Instrutor.
	private Instrutor instrutor;

	public Integer getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "Telefone = " + numero + " cadastradoo para instrutor= " + instrutor + "]";
	}
	
	
}
