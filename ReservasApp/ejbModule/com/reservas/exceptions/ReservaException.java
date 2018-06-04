package com.reservas.exceptions;

public class ReservaException extends Exception{
	private static final long serialVersionUID = 1L;
	public ReservaException(String mensaje) {
		super(mensaje);
	}
}
