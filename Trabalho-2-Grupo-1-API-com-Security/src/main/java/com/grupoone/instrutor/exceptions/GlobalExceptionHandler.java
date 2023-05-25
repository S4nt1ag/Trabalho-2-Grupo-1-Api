package com.grupoone.instrutor.exceptions;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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
	   
	   @ExceptionHandler(IdNotFoundException.class)
	    ProblemDetail handleIdNotFoundException(IdNotFoundException e) {
	    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

	    problemDetail.setTitle("O id do Instrutor não existe no banco de dados");
	        problemDetail.setType(URI.create("https://api.trabalho1.com/errors/not-found"));
	        return problemDetail;
	    }

	   
	   @Override
	    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, 
	    		HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
	        ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, statusCode, request);

	        if (response.getBody() instanceof ProblemDetail problemDetailBody) {
	            problemDetailBody.setProperty("message", ex.getMessage());
	            if (ex instanceof MethodArgumentNotValidException subEx) {
	                BindingResult result = subEx.getBindingResult();
	                problemDetailBody.setTitle("Erro na requisição");
	                problemDetailBody.setDetail("Ocorreu um erro ao processar a Requisição");
	                problemDetailBody.setProperty("message", "Validation failed for object='" + 
	                		result.getObjectName() + "'. " + "Error count: " + result.getErrorCount());
	                
	                problemDetailBody.setProperty("errors", result.getAllErrors());
	            }
	        }
	        return response;
	    }
}

