package com.turismo.coreservices;

import javax.ejb.Local;

//import com.reservas.dto.AgenciaDTO;

@Local
public interface AgenciaServiceLocal {
	public void guardarAgencia(String nombre,String direccion,String codigo_agencia);
}
