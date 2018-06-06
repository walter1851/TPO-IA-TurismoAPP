package com.turismo.services;

import java.io.InputStream;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dao.impl.OfertaDAO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.services.OfertaServiceInterfaceLocal;
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