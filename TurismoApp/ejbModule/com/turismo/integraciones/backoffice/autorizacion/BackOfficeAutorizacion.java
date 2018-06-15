package com.turismo.integraciones.backoffice.autorizacion;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
@WebServiceClient(name = "PrestadorAutorizadoService", 
                  wsdlLocation = "file:/home/ebarbin/wildfly-11.0.0.Beta1/bin/bo.wsdl",
                  targetNamespace = "http://soap.servicios/") 
public class BackOfficeAutorizacion extends Service {
    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.servicios/", "PrestadorAutorizadoService");
    public final static QName backOfficeAutorizacion_PrestadorPort = new QName("http://soap.servicios/", "ServiciosSOAP/PrestadorAutorizadoPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/home/ebarbin/wildfly-11.0.0.Beta1/bin/bo.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BackOfficeAutorizacion.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/home/ebarbin/wildfly-11.0.0.Beta1/bin/bo.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public BackOfficeAutorizacion(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BackOfficeAutorizacion(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BackOfficeAutorizacion() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public BackOfficeAutorizacion(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public BackOfficeAutorizacion(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public BackOfficeAutorizacion(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    
    @WebEndpoint(name = "ServiciosSOAP/PrestadorAutorizadoPort")
    public BackOfficeAutorizacionInterface getServiciosBO_002fPrestadorAutorizadoPort() {
        return super.getPort(backOfficeAutorizacion_PrestadorPort, BackOfficeAutorizacionInterface.class);
    }
    @WebEndpoint(name = "ServiciosSOAP/PrestadorAutorizadoPort")
    public BackOfficeAutorizacionInterface getServiciosBO_002fPrestadorAutorizadoPort(WebServiceFeature... features) {
        return super.getPort(backOfficeAutorizacion_PrestadorPort, BackOfficeAutorizacionInterface.class, features);
    }

}
