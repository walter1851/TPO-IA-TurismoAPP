package com.reservas.dao;

import java.io.InputStream;
import java.util.Date;

import javax.ejb.Local;
import com.reservas.entities.Agencia;
import com.reservas.entities.Destino;
import com.reservas.entities.Establecimiento;
import com.reservas.entities.MedioPago;
import com.reservas.entities.Oferta;
import com.reservas.entities.OfertaTipo;

@Local
public interface OfertaDAOInterfaceLocal {
	public void nuevaOferta(String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,InputStream foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo);
	public void actualizarOferta(int oferta_id,String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,InputStream foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo);
	public Oferta buscarPorCodigo(int codigo);
}
