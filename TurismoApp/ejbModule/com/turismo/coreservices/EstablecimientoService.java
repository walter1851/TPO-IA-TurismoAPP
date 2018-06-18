package com.turismo.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.EstablecimientoDAO;
import com.turismo.dao.HotelDAO;
import com.turismo.dao.ImagenDAO;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Estado;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class EstablecimientoService {
	@EJB
	private EstablecimientoDAO establecimientoDAO;
	@EJB
	private HotelDAO hotelDAO;
	@EJB
	private ImagenDAO imagenDAO;

	public Establecimiento guardarEstablecimiento(String nombre, String direccion, String ciudad, Estado estado,
			String descripcion, int estrellas, String mapa, int codigo_establecimiento, int idHotel,
			String nombreHotel, String urlFotoHotel) {
		Hotel hotel = buscarHotel(idHotel);
		Establecimiento establecimiento = buscarEstablecimiento(codigo_establecimiento);
		Imagen imagen = buscarImagen(urlFotoHotel);
		if (hotel == null)
			hotel = hotelDAO.nuevoHotel(nombreHotel, idHotel);
		// else
		// Actualizar hotel

		if (establecimiento == null)
			establecimiento = establecimientoDAO.nuevoEstablecimiento(nombre, direccion, ciudad, estado, descripcion,
					estrellas, mapa, codigo_establecimiento, hotel);
		// else
		// Actualizar establecimiento

		if (imagen == null)
			imagen = imagenDAO.nuevaImagen(urlFotoHotel, establecimiento, hotel);
		// else
		// Actualizar establecimiento
		return establecimiento;
	}

	private Hotel buscarHotel(int codigo_hotel) {
		Hotel hotelFromDatabase = hotelDAO.buscarPorCodigoHotel(codigo_hotel);
		return hotelFromDatabase;
	}

	private Establecimiento buscarEstablecimiento(int codigo_establecimiento) {
		Establecimiento establecimientoFromDatabase = establecimientoDAO
				.buscarPorCodigoEstablecimiento(codigo_establecimiento);
		return establecimientoFromDatabase;
	}

	private Imagen buscarImagen(String url) {
		Imagen imagenFromDatabase = imagenDAO.buscarImagenPorURL(url);
		return imagenFromDatabase;
	}
}
