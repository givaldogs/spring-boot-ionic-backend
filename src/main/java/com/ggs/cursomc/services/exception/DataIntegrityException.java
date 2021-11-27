package com.ggs.cursomc.services.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
		
	}
}
