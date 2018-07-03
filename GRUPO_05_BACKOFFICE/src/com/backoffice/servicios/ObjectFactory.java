
package com.backoffice.servicios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.backoffice.servicios package. 
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

    private final static QName _EstaAutorizado_QNAME = new QName("http://servicios.backoffice.com/", "estaAutorizado");
    private final static QName _EstaAutorizadoResponse_QNAME = new QName("http://servicios.backoffice.com/", "estaAutorizadoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.backoffice.servicios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EstaAutorizado }
     * 
     */
    public EstaAutorizado createEstaAutorizado() {
        return new EstaAutorizado();
    }

    /**
     * Create an instance of {@link EstaAutorizadoResponse }
     * 
     */
    public EstaAutorizadoResponse createEstaAutorizadoResponse() {
        return new EstaAutorizadoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstaAutorizado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.backoffice.com/", name = "estaAutorizado")
    public JAXBElement<EstaAutorizado> createEstaAutorizado(EstaAutorizado value) {
        return new JAXBElement<EstaAutorizado>(_EstaAutorizado_QNAME, EstaAutorizado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstaAutorizadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servicios.backoffice.com/", name = "estaAutorizadoResponse")
    public JAXBElement<EstaAutorizadoResponse> createEstaAutorizadoResponse(EstaAutorizadoResponse value) {
        return new JAXBElement<EstaAutorizadoResponse>(_EstaAutorizadoResponse_QNAME, EstaAutorizadoResponse.class, null, value);
    }

}
