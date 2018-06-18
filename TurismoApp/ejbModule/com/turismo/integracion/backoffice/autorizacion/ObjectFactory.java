
package com.turismo.integracion.backoffice.autorizacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPrestadorAutorizado_QNAME = new QName("http://soap.servicios/", "getPrestadorAutorizado");
    private final static QName _GetPrestadorAutorizadoResponse_QNAME = new QName("http://soap.servicios/", "getPrestadorAutorizadoResponse");

    public ObjectFactory() {
    }
    public GetPrestadorAutorizado createGetPrestadorAutorizado() {
        return new GetPrestadorAutorizado();
    }
    public GetPrestadorAutorizadoResponse createGetPrestadorAutorizadoResponse() {
        return new GetPrestadorAutorizadoResponse();
    }
    public ResponseMensaje createSolicitudDTO() {
        return new ResponseMensaje();
    }
    @XmlElementDecl(namespace = "http://soap.servicios/", name = "getPrestadorAutorizado")
    public JAXBElement<GetPrestadorAutorizado> createGetPrestadorAutorizado(GetPrestadorAutorizado value) {
        return new JAXBElement<GetPrestadorAutorizado>(_GetPrestadorAutorizado_QNAME, GetPrestadorAutorizado.class, null, value);
    }
    @XmlElementDecl(namespace = "http://soap.servicios/", name = "getPrestadorAutorizadoResponse")
    public JAXBElement<GetPrestadorAutorizadoResponse> createGetPrestadorAutorizadoResponse(GetPrestadorAutorizadoResponse value) {
        return new JAXBElement<GetPrestadorAutorizadoResponse>(_GetPrestadorAutorizadoResponse_QNAME, GetPrestadorAutorizadoResponse.class, null, value);
    }

}
