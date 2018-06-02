package com.reservas.businessdelegate;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.bean.dto.MedioPagoDTO;
import com.reservas.bean.dto.OfertaDTO;
import com.reservas.controller.Controller;

@Stateless
@LocalBean
public class BusinessDelegate{
	@EJB
	Controller controller;
	public void reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, MedioPagoDTO medioPagoDTO) {
		controller.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}

	public void reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, MedioPagoDTO medioPagoDTO) {
		controller.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws ParseException {
		return controller.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta, tipoHabitacion);
	}

	public List<OfertaDTO> buscarOfertaPaquete(String destino, int cantPersonas, String fDesde, String fHasta) throws ParseException {
		return controller.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
	}
}
