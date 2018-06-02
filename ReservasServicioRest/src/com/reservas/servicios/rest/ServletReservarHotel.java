package com.reservas.servicios.rest;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.reservas.bean.dto.MedioPagoDTO;
import com.reservas.businessdelegate.BusinessDelegate;

@WebServlet("/reservar/paquete")
public class ServletReservarHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BusinessDelegate bussinessDelegate;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ofertaid = Integer.parseInt(req.getParameter("ofertaid").toString());
		String fDesde = req.getParameter("fDesde").toString();
		String fHasta = req.getParameter("fhasta").toString();
		String tipoHabitacion = req.getParameter("tipoHabitacion").toString();
		int cantPersonas = Integer.parseInt(req.getParameter("cantPersonas").toString());
		String nombre = req.getParameter("nombre").toString();
		String apellido = req.getParameter("apellido").toString();
		String dni = req.getParameter("dni").toString();
		String medioPago= req.getParameter("medioPago").toString();
		//Contruir el dto para pasarlo al business delegate
		MedioPagoDTO medioPagoDTO=null;
		bussinessDelegate.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}
}
