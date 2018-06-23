package com.turismo.qconsumer.mensajes;

public class AgenciaMensaje {
		private String id;
		private String nombre;
		private String direccion;
		//EL ESTADO NO VA
		//private String estado; // INACTIVO, ACTIVO
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
}
