package com.turismo.integracion.backoffice.logging;

public enum LoggingAccion {
	RESERVA_DE_HOTEL(1, "Nueva reserva de hotel"),
	RESERVA_DE_PAQUETE(2, "Reserva Paquete"),
	NUEVA_PUBLICACION_OFERTA_HOTELERA(3, "Registro Oferta Hotelera"),
	NUEVA_PUBLICACION_OFERTA_PAQUETE(4, "Registro Oferta Paquete"),
	BUSQUEDA_OFERTA_HOTELERA(5, "Buscar Oferta Hotelera"),
	BUSQUEDA_OFERTA_PAQUETE(6, "Buscar Oferta Paquete"),
	VISUALIZAR_DETALLE_OFERTA_HOTELERA(7, "Ver Detalle Oferta Hotelera"),
	VISUALIZAR_DETALLE_OFERTA_PAQUETE(8, "Ver Detalle Oferta Paquete"),
	ERROR(10, "ERROR");
	
	private Integer idAccion;
	
	private String description;
	
	private LoggingAccion(Integer id, String description) {
		this.idAccion = id;
		this.description = description;
	}

	public Integer getId() {
		return idAccion;
	}

	public String getDescription() {
		return description;
	}
}
