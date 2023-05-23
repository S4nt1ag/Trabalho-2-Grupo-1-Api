package com.grupoone.instrutor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupoone.instrutor.entities.Instrutor;

public interface InstrutorRepository extends JpaRepository <Instrutor, Integer> {

}
