package com.turismo.coreservices;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.DestinoDAO;
import com.turismo.dao.HotelDAO;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
@Stateless
@LocalBean
public class OfertaService{
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private DestinoDAO destinoDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private EstablecimientoService establecimientoService;
	@EJB
	private HotelDAO hotelDAO;
	@EJB
	private AgenciaService agenciaService;
	
public void guardarOfertaPaquete(String nombreOferta,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String politicas,String servicios,String nombreDestino,String foto_paquete,MedioPago medioPago, int cant_personas,String codigo_agencia, String direccionAgencia,String nombreAgencia,OfertaTipo ofertaTipo) {
	Destino destino=destinoDAO.buscarDestinoPorNombre(nombreDestino);
	Agencia agencia=agenciaService.guardarAgencia(codigo_agencia, direccionAgencia,nombreAgencia);
	Oferta nuevaOferta=ofertaDAO.nuevaOfertaPaquete(nombreOferta, cupo, fecha_desde, fecha_hasta, precio, politicas, servicios, destino, foto_paquete, medioPago, cant_personas, agencia, ofertaTipo);
	//Falta agregar los bloques de acuerdo a la cantidad de personas y las fechas
	ofertaBloqueDAO.nuevoBloque(nuevaOferta, null, cupo);
} 
public void guardarOfertaHotelera(String nombreOferta,String nombreDestino,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,String foto_paquete,MedioPago medioPago, int cant_personas,String nombreEstablecimiento, String direccionEstablecimiento,String ciudadEstablecimiento,String estadoEstablecimiento, String descripcionEstablecimiento, String estrellasEstablecimiento,String mapaEstablecimiento,String codigo_establecimiento, String nombreHotel, String codigo_hotel,OfertaTipo ofertaTipo) {
	Destino destino=destinoDAO.buscarDestinoPorNombre(nombreDestino);
	Hotel hotel=hotelDAO.nuevoHotel(nombreHotel, codigo_hotel);
	Establecimiento establecimiento=establecimientoService.guardarEstablecimiento(nombreEstablecimiento, direccionEstablecimiento, ciudadEstablecimiento, estadoEstablecimiento, descripcionEstablecimiento, estrellasEstablecimiento, mapaEstablecimiento, codigo_establecimiento, hotel);
	Oferta nuevaOferta=ofertaDAO.nuevaOfertaHotelera(nombreOferta, cupo, fecha_desde, fecha_hasta, precio, tipo_habitacion, politicas, servicios, destino, foto_paquete, medioPago, cant_personas, establecimiento, ofertaTipo);
	//Falta agregar los bloques de acuerdo a la cantidad de personas y las fechas
	ofertaBloqueDAO.nuevoBloque(nuevaOferta, null, cupo);
} 
}
