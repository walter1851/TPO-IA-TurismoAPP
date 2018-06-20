package com.turismo.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.EstablecimientoDAO;
import com.turismo.dao.HotelDAO;
import com.turismo.dao.ImagenDAO;
import com.turismo.entities.Establecimiento;
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

	public Establecimiento guardarEstablecimiento(String nombre, String direccion, String ciudad, String descripcion, int estrellas, String latitud, String longitud, int codigo_establecimiento, int codigo_hotel,
			String nombreHotel, String imagenBase64) {
		Hotel hotel = hotelDAO.buscarPorCodigoHotel(codigo_hotel);
		Establecimiento establecimiento = establecimientoDAO.buscarPorCodigoEstablecimiento(codigo_establecimiento);
		Imagen imagen =  imagenDAO.buscarImagenPorURL(imagenBase64);
		
		if (hotel == null)
			hotel = hotelDAO.nuevoHotel(nombreHotel, codigo_hotel);

		if (establecimiento == null)
			establecimiento = establecimientoDAO.nuevoEstablecimiento(nombre, direccion, ciudad, descripcion,
					estrellas, latitud,longitud, codigo_establecimiento, hotel);

		if (imagen == null)
			imagen = imagenDAO.nuevaImagen(imagenBase64, establecimiento, hotel);

		return establecimiento;
	}
}
