package com.grupoone.instrutor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoone.instrutor.entities.Telefone;
import com.grupoone.instrutor.exceptions.NoSuchElementException;
import com.grupoone.instrutor.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	TelefoneRepository telefoneRepository;
	
	@Autowired
	EmailService emailService;

	public List<Telefone> getAllTelefones() {
		return telefoneRepository.findAll();
	}

	public Telefone getTelefoneById(Integer id) {
		return telefoneRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
	}

	public Telefone saveTelefone(Telefone telefone) {
		Boolean instrutorLivre = true;
		List<Telefone> listaTelefones = this.getAllTelefones();
		
		for(Telefone tel : listaTelefones) {
			if(tel.getInstrutor().getIdInstrutor() == telefone.getInstrutor().getIdInstrutor()) {
				instrutorLivre = false;
			}	
		}
		if(instrutorLivre) {
			Telefone novoTelefone = telefoneRepository.save(telefone);
			emailService.enviarEmail("email@outlook.com", "Telefone cadastrado", novoTelefone.toString());
			return novoTelefone;
		}
		else {
			return null;
		}
		
	}

	public Telefone updateTelefone(Telefone telefone, Integer id) {
		Boolean instrutorLivre = true;
		List<Telefone> listaTelefones = this.getAllTelefones();
		
		for(Telefone t : listaTelefones) {
			if(t.getInstrutor().getIdInstrutor() == telefone.getInstrutor().getIdInstrutor()) {
				instrutorLivre = false;
			}	
		}
		if(instrutorLivre) {
			return telefoneRepository.save(telefone);
		}
		else {
			return null;
		}
	}

	public Boolean deleteTelefone(Integer id) {
		Telefone telefoneDeleted = telefoneRepository.findById(id).orElse(null);
		if (telefoneDeleted != null) {
			telefoneRepository.deleteById(id);
			telefoneDeleted = telefoneRepository.findById(id).orElse(null);
			if (telefoneDeleted != null) {
				return false;
			} 
			else {
				return true;
			}

		} 
		else {
			return false;
		}
	}
}
