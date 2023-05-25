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

import com.grupoone.instrutor.entities.Instrutor;
import com.grupoone.instrutor.services.InstrutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {

	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping
	public ResponseEntity<List<Instrutor>> getAllInstrutores() {
		return new ResponseEntity<>(instrutorService.getAllInstrutores(),
				HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> getInstrutorById(@Valid @PathVariable Integer id) {
			return new ResponseEntity<>(instrutorService.getInstrutorById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@Valid @RequestBody Instrutor instrutor) {
		
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor),
				HttpStatus.CREATED);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Instrutor> updateInstrutor(@Valid @RequestBody Instrutor instrutor,@Valid @PathVariable Integer id) {
		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor, id),
				HttpStatus.OK);
	}

//	@PutMapping
//	public ResponseEntity<Instrutor> updateInstrutor(@RequestBody Instrutor instrutor, Integer id) {
//		return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor, id),
//				HttpStatus.OK);
//	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteInstrutor(@PathVariable Integer id) {
		Boolean resp = instrutorService.deleteInstrutor(id);
		if(resp)
			return new ResponseEntity<>(resp, HttpStatus.OK);
		else
			return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
	}
}
	