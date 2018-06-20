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
			String descripcion, int estrellas, String latitud, String longitud, int codigo_establecimiento, int idHotel,
			String nombreHotel, String imagenBase64) {
		Hotel hotel = buscarHotel(idHotel);
		Establecimiento establecimiento = buscarEstablecimiento(codigo_establecimiento);
		Imagen imagen = buscarImagen(imagenBase64);
		if (hotel == null)
			hotel = hotelDAO.nuevoHotel(nombreHotel, idHotel);

		if (establecimiento == null)
			establecimiento = establecimientoDAO.nuevoEstablecimiento(nombre, direccion, ciudad, estado, descripcion,
					estrellas, latitud,longitud, codigo_establecimiento, hotel);

		if (imagen == null)
			imagen = imagenDAO.nuevaImagen(imagenBase64, establecimiento, hotel);

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

	private Imagen buscarImagen(String imagenBase64) {
		Imagen imagenFromDatabase = imagenDAO.buscarImagenPorURL(imagenBase64);
		return imagenFromDatabase;
	}
}
