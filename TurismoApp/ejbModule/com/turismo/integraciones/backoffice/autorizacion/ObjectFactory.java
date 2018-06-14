
package com.turismo.integraciones.backoffice.autorizacion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicios.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPrestadorAutorizado_QNAME = new QName("http://soap.servicios/", "getPrestadorAutorizado");
    private final static QName _GetPrestadorAutorizadoResponse_QNAME = new QName("http://soap.servicios/", "getPrestadorAutorizadoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicios.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPrestadorAutorizado }
     * 
     */
    public GetPrestadorAutorizado createGetPrestadorAutorizado() {
        return new GetPrestadorAutorizado();
    }

    /**
     * Create an instance of {@link GetPrestadorAutorizadoResponse }
     * 
     */
    public GetPrestadorAutorizadoResponse createGetPrestadorAutorizadoResponse() {
        return new GetPrestadorAutorizadoResponse();
    }

    /**
     * Create an instance of {@link SolicitudDTO }
     * 
     */
    public SolicitudDTO createSolicitudDTO() {
        return new SolicitudDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrestadorAutorizado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.servicios/", name = "getPrestadorAutorizado")
    public JAXBElement<GetPrestadorAutorizado> createGetPrestadorAutorizado(GetPrestadorAutorizado value) {
        return new JAXBElement<GetPrestadorAutorizado>(_GetPrestadorAutorizado_QNAME, GetPrestadorAutorizado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPrestadorAutorizadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.servicios/", name = "getPrestadorAutorizadoResponse")
    public JAXBElement<GetPrestadorAutorizadoResponse> createGetPrestadorAutorizadoResponse(GetPrestadorAutorizadoResponse value) {
        return new JAXBElement<GetPrestadorAutorizadoResponse>(_GetPrestadorAutorizadoResponse_QNAME, GetPrestadorAutorizadoResponse.class, null, value);
    }

}
