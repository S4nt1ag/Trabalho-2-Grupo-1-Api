package com.grupoone.instrutor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupoone.instrutor.dto.TurmaDTO;
import com.grupoone.instrutor.entities.Turma;
import com.grupoone.instrutor.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<TurmaDTO>> getAllTurmas() {
		return new ResponseEntity<>(turmaService.getAllTurmas(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TurmaDTO> getTurmaById(@PathVariable Integer id) {
		return new ResponseEntity<>(turmaService.getTurmaById(id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Turma> updateInstrutor(@RequestBody Turma turma, @PathVariable Integer id) {
		return new ResponseEntity<>(turmaService.updateTurma(turma, id), HttpStatus.OK);
	}

//	@PutMapping
//	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma, Integer id) {
//		return new ResponseEntity<>(turmaService.updateTurma(turma, id),
//				HttpStatus.OK);
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTurma(@PathVariable Integer id) {
		Boolean resp = turmaService.deleteTurma(id);
		if (resp)
			return new ResponseEntity<>(resp, HttpStatus.OK);
		else
			return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
	}

}