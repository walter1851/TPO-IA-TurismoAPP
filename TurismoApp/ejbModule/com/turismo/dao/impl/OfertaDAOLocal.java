package com.turismo.dao.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;

@Local
public interface OfertaDAOLocal {
	public void nuevaOferta(String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,String foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo);
	public void actualizarOferta(int oferta_id,String nombre,int cupo,Date fecha_desde,Date fecha_hasta,float precio,String tipo_habitacion,String politicas,String servicios,Destino destino,String foto_paquete,MedioPago medioPago, int cant_personas,Establecimiento establecimiento,Agencia agencia,OfertaTipo ofertaTipo);
	public Oferta buscarPorCodigo(int codigo);
	public List<Oferta> buscarOfertasHotelera(String destino,int cantPersonas,String fDesde, String fHasta);
	public List<Oferta> buscarOfertasPaquete(String destino,int cantPersonas,String fDesde, String fHasta);
}
