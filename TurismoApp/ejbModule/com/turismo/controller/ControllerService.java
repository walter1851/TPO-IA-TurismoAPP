package com.turismo.controller;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import com.turismo.coreservices.BusquedaService;
import com.turismo.coreservices.ReservaService;
import com.turismo.dto.OfertaDTO;
import com.turismo.dto.ReservaDTO;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.exceptions.ReservaException;
@Stateless
@LocalBean
public class ControllerService{
	@EJB
	private BusquedaService busquedaOfertaService;
	@EJB
	private ReservaService reservaService;
	
	public List<OfertaDTO> buscarOfertaPaquete(int codigoDestino,int cantPersonas,String fDesde, String fHasta) throws OfertaPaqueteException, ConversionFechaException{
		return busquedaOfertaService.buscarOfertaPaquete(codigoDestino, cantPersonas, fDesde, fHasta);
	}
	public List<OfertaDTO> buscarOfertaHotelera(int codigoDestino,String fDesde, String fHasta, String tipoHabitacion,int cantTotalPersonas) throws OfertaHoteleraException, ConversionFechaException {
		return busquedaOfertaService.buscarOfertaHotelera(codigoDestino, fDesde, fHasta, tipoHabitacion,cantTotalPersonas);
	}
	public ReservaDTO reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantTotalPersonas,String nombre,String apellido,String dni,int medioPagoId,String mailUsuario) throws ReservaException, ConversionFechaException, OfertaHoteleraException, OfertaPaqueteException {
		return reservaService.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantTotalPersonas, nombre, apellido, dni, medioPagoId,mailUsuario);
	}
	public ReservaDTO reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,int medioPagoId,String mailUsuario) throws ReservaException, RemoteException, ServiceException, ConversionFechaException, OfertaPaqueteException {
		return reservaService.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoId,mailUsuario);
	}
	public float calcularPrecioTotalPaquete(int ofertaId, int cantidadPersonas) throws OfertaPaqueteException {
		return busquedaOfertaService.calcularPrecioTotalPaquete(ofertaId, cantidadPersonas);
	}
	public float calcularPrecioTotalHotel(int ofertaId, String tipoHabitacion, int cantidadTotalPersonas,String fDesde, String fHasta) throws OfertaHoteleraException, ConversionFechaException, OfertaPaqueteException {
		return busquedaOfertaService.calcularPrecioTotalHotel(ofertaId, tipoHabitacion,cantidadTotalPersonas, fDesde, fHasta);
	}
	public List<OfertaDTO> buscarOtrosPaquetesMismoDestino(int id_paquete_a_excluir, int codigo_destino,
			int cantPersonas, String fDesdeString, String fHastaString) throws ConversionFechaException, OfertaPaqueteException{
		return busquedaOfertaService.buscarOtrosPaquetesMismoDestino(id_paquete_a_excluir, codigo_destino, cantPersonas, fDesdeString, fHastaString);
	}
	//Otras habitaciones disponibles del mismo hotel
	public List<OfertaDTO> buscarOtrasOfertasMismoHotel(int codigo_destino, String tipo_Habitacion_a_excluir,
			int id_hotel, String fDesde, String fHasta,int cantTotalPersonas) throws ConversionFechaException, OfertaHoteleraException{
		return busquedaOfertaService.buscarOtrasOfertasMismoHotel(codigo_destino, tipo_Habitacion_a_excluir, id_hotel, fDesde, fHasta,cantTotalPersonas);
	}
	public boolean prestadorEstaAutorizado(String codigo_prestador){
		return reservaService.prestadorEstaAutorizado(codigo_prestador);
	}
}
