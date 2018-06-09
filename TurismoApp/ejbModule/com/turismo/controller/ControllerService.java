package com.turismo.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.controller.ControllerServiceLocal;
import com.turismo.coreservices.BusquedaService;
import com.turismo.coreservices.ReservaService;
import com.turismo.dto.OfertaDTO;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
@Stateless
@LocalBean
public class ControllerService implements ControllerServiceLocal{
	@EJB
	private BusquedaService busquedaOfertaService;
	@EJB
	private ReservaService reservaService;
	
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws OfertaPaqueteException{
		return busquedaOfertaService.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta, String tipoHabitacion) throws OfertaHoteleraException {
		return busquedaOfertaService.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta, tipoHabitacion);
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
		reservaService.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPago);
	}
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
		reservaService.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPago);
	}
}
