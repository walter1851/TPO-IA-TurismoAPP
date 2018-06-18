package com.turismo.integracion.backoffice.autorizacion;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;
@WebServiceClient(name = "PrestadorAutorizadoService", 
                  wsdlLocation = "file:/???",
                  targetNamespace = "http://soap.servicios/") 
public class BackOfficeAutorizacion extends Service {
    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://soap.servicios/", "PrestadorAutorizadoService");
    public final static QName backOfficeAutorizacion_PrestadorPort = new QName("http://soap.servicios/", "ServiciosSOAP/PrestadorAutorizadoPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/???");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BackOfficeAutorizacion.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "no se puede inicializar wsfld desde el archivo {0}", "file:???");
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
    public BackOfficeAutorizacionInterface getServicioPrestadorAutorizadoPort() {
        return super.getPort(backOfficeAutorizacion_PrestadorPort, BackOfficeAutorizacionInterface.class);
    }
    @WebEndpoint(name = "ServiciosSOAP/PrestadorAutorizadoPort")
    public BackOfficeAutorizacionInterface getServicioPrestadorAutorizadoPort(WebServiceFeature... features) {
        return super.getPort(backOfficeAutorizacion_PrestadorPort, BackOfficeAutorizacionInterface.class, features);
    }

}
