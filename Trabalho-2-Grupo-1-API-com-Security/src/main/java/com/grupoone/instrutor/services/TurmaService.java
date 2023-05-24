package com.grupoone.instrutor.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.grupoone.instrutor.dto.TurmaDTO;
import com.grupoone.instrutor.entities.Instrutor;
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
	private ModelMapper modelMapper;

	@Autowired
	InstrutorRepository instrutorRepository;

	@Autowired
	EmailService emailService;

	public List<TurmaDTO> getAllTurmas() {
		List<Turma> listaTurmas = turmaRepository.findAll();
		List<TurmaDTO> listaTurDto = modelMapper.map(listaTurmas, new TypeToken<List<TurmaDTO>>() {
		}.getType());

		for (int i = 0; i < listaTurmas.size(); i++) {
			Instrutor instrutor = listaTurmas.get(i).getInstrutor();
			String nomeInstrutor = instrutor.getNome();
			listaTurDto.get(i).getInstrutorDto().setNome(nomeInstrutor);
		}

		return listaTurDto;
	}

	public TurmaDTO getTurmaById(Integer id) {
		Turma turma = turmaRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
		TurmaDTO turmaDto = new TurmaDTO();

		turmaDto.setDiaSemana(turma.getDiaSemana());
		turmaDto.setNomeDisciplina(turma.getNomeDisciplina());
		turmaDto.getInstrutorDto().setNome(turma.getInstrutor().getNome());

		return turmaDto;
	}

	public Turma saveTurma(Turma turma) {
		Turma novaTurma = turmaRepository.save(turma);
		emailService.enviarEmail("email@outlook.com", "Turma cadastrada", novaTurma.toString());

		return novaTurma;
	}

	public Turma updateTurma(Turma turma, Integer id) {
		try {
			if (instrutorRepository.existsById(id)) {
				Turma updateTurma = turmaRepository.getReferenceById(id);
				updateData(updateTurma, turma);
				return turmaRepository.save(updateTurma);
			} 
			else {
				throw new NoSuchElementException("");
			}
		} 
		catch (DataAccessException e) {
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