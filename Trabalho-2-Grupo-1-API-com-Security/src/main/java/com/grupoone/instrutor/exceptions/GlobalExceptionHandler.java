package com.grupoone.instrutor.exceptions;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	
	   @ExceptionHandler(NoSuchElementException.class)
	    ProblemDetail handleNoSuchElementException(NoSuchElementException e) {
	    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

	    problemDetail.setTitle("Recurso Não Encontrado");
	        problemDetail.setType(URI.create("https://api.trabalho1.com/errors/not-found"));
	        return problemDetail;
	    }
}

