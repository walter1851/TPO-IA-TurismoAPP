package com.reservas.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservas.businessdelegate.BusinessDelegate;
import com.reservas.dto.OfertaDTO;

@WebServlet("/reserva")
public class ServletOfertaHotelera extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BusinessDelegate bussinessDelegate;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String destino = req.getParameter("destino");
			int cantPersonas = Integer.parseInt(req.getParameter("cantPersonas").toString());
			String fDesde = req.getParameter("fDesde");
			String fHasta = req.getParameter("fHasta");
			String tipoHabitacion = req.getParameter("tipoHabitacion");
			List<OfertaDTO> ofertas = bussinessDelegate.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta,
					tipoHabitacion);
			//construir JSON y pasarlo al frontend
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
