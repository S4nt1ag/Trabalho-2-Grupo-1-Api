package com.grupoone.instrutor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.grupoone.instrutor.entities.Turma;
import com.grupoone.instrutor.exceptions.IdNotFoundException;
import com.grupoone.instrutor.exceptions.NoSuchElementException;
import com.grupoone.instrutor.repositories.InstrutorRepository;
import com.grupoone.instrutor.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	InstrutorRepository instrutorRepository;
	
	@Autowired
	EmailService emailService;


	public List<Turma> getAllTurmas() {
		return turmaRepository.findAll();
	}

	public Turma getTurmaById(Integer id) {
		return turmaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
		
	}

	public Turma saveTurma(Turma turma) {
		Turma novaTurma=turmaRepository.save(turma);
		emailService.enviarEmail("email@outlook.com", "Turma cadastrada", novaTurma.toString());
		
		return novaTurma;
	}

	public Turma updateTurma(Turma turma, Integer id) {
		try {
	        if (instrutorRepository.existsById(id)) {
	            Turma updateTurma = turmaRepository.getReferenceById(id);
	            updateData(updateTurma, turma);
	            return turmaRepository.save(updateTurma);
	        } else {
	            throw new NoSuchElementException("");
	        }
	    } catch (DataAccessException e) {
	    	    throw new IdNotFoundException("Id not found: " + id);
	    }
	}

	private void updateData(Turma updateTurma, Turma turma) {
		updateTurma.setNomeDisciplina(turma.getNomeDisciplina());
		updateTurma.setDiaSemana(turma.getDiaSemana());
		updateTurma.setInstrutor(turma.getInstrutor());
	}
	
	

	public Boolean deleteTurma(Integer id) {
		Turma turmaDeleted = turmaRepository.findById(id).orElse(null);
		if (turmaDeleted != null) {
			turmaRepository.deleteById(id);
			turmaDeleted = turmaRepository.findById(id).orElse(null);
			if (turmaDeleted != null) {
				return false;
			} else {
				return true;
			}

		} else {
			return false;
		}
	}

}
