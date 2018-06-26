package com.turismo.exceptions;

public class LoggingException extends Exception{
	private static final long serialVersionUID = 1L;

	public LoggingException(String mensaje) {
		super(mensaje);
	}
}
