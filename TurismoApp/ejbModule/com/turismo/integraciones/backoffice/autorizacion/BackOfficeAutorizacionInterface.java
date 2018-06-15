package com.turismo.integraciones.backoffice.autorizacion;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://soap.servicios/", name = "ServiciosSOAP/PrestadorAutorizado")
@XmlSeeAlso({ObjectFactory.class})
public interface BackOfficeAutorizacionInterface {

    @WebMethod
    @RequestWrapper(localName = "getPrestadorAutorizado", targetNamespace = "http://soap.servicios/", className = "servicios.soap.GetPrestadorAutorizado")
    @ResponseWrapper(localName = "getPrestadorAutorizadoResponse", targetNamespace = "http://soap.servicios/", className = "servicios.soap.GetPrestadorAutorizadoResponse")
    @WebResult(name = "solicitud", targetNamespace = "")
    public ResponseMensaje getPrestadorAutorizado(
        @WebParam(name = "id", targetNamespace = "")
        int id
    );
}
