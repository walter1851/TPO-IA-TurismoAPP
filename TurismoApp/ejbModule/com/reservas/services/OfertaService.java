package com.reservas.services;

import java.io.InputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.impl.OfertaDAO;
import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Agencia;
import com.reservas.entities.Destino;
import com.reservas.entities.Establecimiento;
import com.reservas.entities.MedioPago;
import com.reservas.entities.Oferta;
import com.reservas.entities.OfertaTipo;
import com.reservas.services.OfertaServiceInterfaceLocal;
@Stateless
@LocalBean
public class OfertaService implements OfertaServiceInterfaceLocal{
	@EJB
	OfertaDAO ofertaDAO;
public void guardarOfertaPaquete(String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,InputStream foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo) {

} 
public void guardarOfertaHotelera(String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,InputStream foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo) {

} 
}
