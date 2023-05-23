package com.grupoone.instrutor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoone.instrutor.entities.Instrutor;
import com.grupoone.instrutor.repositories.InstrutorRepository;

@Service
public class InstrutorService {


	@Autowired
	InstrutorRepository instrutorRepository;

	public List<Instrutor> getAllInstrutores() {
		return instrutorRepository.findAll();
	}

	public Instrutor getInstrutorById(Integer Id) {
		return instrutorRepository.findById(Id).orElse(null);
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor, Integer id) {
		return instrutorRepository.save(instrutor);
	}

	public Boolean deleteInstrutor(Integer id) {
		Instrutor instrutorDeleted = instrutorRepository.findById(id).orElse(null);
		if (instrutorDeleted != null) {
			instrutorRepository.deleteById(id);
			instrutorDeleted = instrutorRepository.findById(id).orElse(null);
			if (instrutorDeleted != null) {
				return false;
			} else {
				return true;
			}
			
		} else {
			return false;
		}
	}

}
