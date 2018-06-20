package com.turismo.dto;

public enum TipoHabitacionDTO {
	SIMPLE(1, "SIMPLE"),
	DOBLE(2, "DOBLE"),
	TRIPLE(3, "TRIPLE");
	
	private Integer maxCantPersonas;
	private String tipoHabitacion;
	
	private TipoHabitacionDTO(Integer maxCantPersonas, String tipoHabitacion) {
		this.maxCantPersonas = maxCantPersonas;
		this.tipoHabitacion = tipoHabitacion;
	}

	public Integer getMaxCantPersonas() {
		return maxCantPersonas;
	}

	public void setMaxCantPersonas(Integer maxCantPersonas) {
		this.maxCantPersonas = maxCantPersonas;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
}
