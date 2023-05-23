package com.grupoone.instrutor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupoone.instrutor.entities.Turma;
import com.grupoone.instrutor.repositories.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	EmailService emailService;


	public List<Turma> getAllTurmas() {
		return turmaRepository.findAll();
	}

	public Turma getTurmaById(Integer id) {
		return turmaRepository.findById(id).orElse(null);
	}

	public Turma saveTurma(Turma turma) {
		Turma novaTurma=turmaRepository.save(turma);
		emailService.enviarEmail("email@outlook.com", "Turma cadastrada", novaTurma.toString());
		
		return novaTurma;
	}

	public Turma updateTurma(Turma turma, Integer id) {
		return turmaRepository.save(turma);
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
