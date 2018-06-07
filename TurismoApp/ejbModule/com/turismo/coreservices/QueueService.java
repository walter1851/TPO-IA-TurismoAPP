package com.turismo.coreservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.ColaServiceLocal;

@Stateless
@LocalBean
public class QueueService implements ColaServiceLocal {

	public QueueService() {
	}
	public void guardarOfertaHotelera(){
		
	}
}
