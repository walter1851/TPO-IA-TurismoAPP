package com.reservas.servicios.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reservas.bean.dto.OfertaDTO;
import com.reservas.businessdelegate.BusinessDelegate;

@WebServlet("/reserva")
public class ServletOfertaPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BusinessDelegate bussinessDelegate;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String destino = req.getParameter("destino");
			int cantPersonas = Integer.parseInt(req.getParameter("cantPersonas").toString());
			String fDesde = req.getParameter("fDesde");
			String fHasta = req.getParameter("fHasta");
			List<OfertaDTO> ofertas = bussinessDelegate.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
			//construir JSON y pasarlo al frontend
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
