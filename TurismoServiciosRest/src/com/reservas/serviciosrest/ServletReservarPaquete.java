package com.reservas.serviciosrest;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservas.businessdelegate.BusinessDelegate;
import com.reservas.dto.MedioPagoDTO;

@WebServlet("/turismo/reservapaquete/reservar")
public class ServletReservarPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BusinessDelegate bussinessDelegate;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int ofertaid = Integer.parseInt(req.getParameter("ofertaid").toString());
		String fDesde = req.getParameter("fDesde").toString();
		String fHasta = req.getParameter("fhasta").toString();
		int cantPersonas = Integer.parseInt(req.getParameter("cantPersonas").toString());
		String nombre = req.getParameter("nombre").toString();
		String apellido = req.getParameter("apellido").toString();
		String dni = req.getParameter("dni").toString();
		String medioPago= req.getParameter("medioPago").toString();
		//Contruir el dto para pasarlo al business delegate
		MedioPagoDTO medioPagoDTO=null;
		bussinessDelegate.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}
}
