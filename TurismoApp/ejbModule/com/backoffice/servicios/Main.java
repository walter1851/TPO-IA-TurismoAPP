package com.backoffice.servicios;

public class Main {

	public static void main(String[] args) {

		SOAPService service = new SOAPService();
		
		//hotel 8079ec53-de70-43f4-b589-cfead878e688 true
		//hotel 342445EE false
		if(service.getSOAPPort().estaAutorizado("8079ec53-de70-43f4-b589-cfead878e688")){
			System.out.println("HOTEL APROBADO (true)!!!!!");
		}
			else {
				System.out.println("HOTEL RECHAZADO (false)!!!!");
			}
		}
	}
