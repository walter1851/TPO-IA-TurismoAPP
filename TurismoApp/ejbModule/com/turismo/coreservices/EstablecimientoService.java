package com.turismo.coreservices;

import java.util.List;
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

	public Establecimiento guardarEstablecimiento(String nombre, String direccion, String ciudad, String descripcion, int estrellas, String latitud, String longitud, String codigo_establecimiento, String codigo_hotel,
			String nombreHotel, String fotoHotel, List<String> fotosEstablecimiento) {
		Hotel hotel = hotelDAO.buscarPorCodigoHotel(codigo_hotel);
		if (hotel == null)
			hotel = hotelDAO.nuevoHotel(nombreHotel, codigo_hotel);
		Establecimiento establecimiento = establecimientoDAO.buscarPorCodigoEstablecimiento(codigo_establecimiento);
		if (establecimiento == null)
			establecimiento = establecimientoDAO.nuevoEstablecimiento(nombre, direccion, ciudad, descripcion,
					estrellas, latitud,longitud, codigo_establecimiento, hotel);
		Imagen imagenHotel =  imagenDAO.buscarImagenHotel(hotel.getHotel_id(),fotoHotel);
		if (imagenHotel == null)
			imagenHotel = imagenDAO.nuevaImagen(fotoHotel, null, hotel);
		Imagen imagenEstablecimiento =  imagenDAO.buscarImagenEstablecimiento(establecimiento.getEstablecimiento_id(),fotosEstablecimiento.get(0));
		if (imagenEstablecimiento == null)
			imagenEstablecimiento = imagenDAO.nuevaImagen(fotosEstablecimiento.get(0), establecimiento, null);

		return establecimiento;
	}
}
