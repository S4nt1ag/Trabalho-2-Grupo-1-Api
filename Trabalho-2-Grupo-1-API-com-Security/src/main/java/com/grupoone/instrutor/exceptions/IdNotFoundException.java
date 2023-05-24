package com.grupoone.instrutor.exceptions;

public class IdNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	 public IdNotFoundException(String message) {
	        super("Id não existe");
	    }

}
