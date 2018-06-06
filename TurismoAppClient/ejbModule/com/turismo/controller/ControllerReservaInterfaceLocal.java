package com.turismo.controller;
import com.turismo.dto.MedioPagoDTO;

public interface ControllerReservaInterfaceLocal {
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago);
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago);
}
