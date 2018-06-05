package com.reservas.clienterest;

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;

public class ClienteRest {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/ReservasServicioRest/turismo/ofertahotelera/buscar");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if (urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		String response = IOUtils.toString(urlConnection.getInputStream());
		System.out.println("Respuesta: " + response);
	}
}
