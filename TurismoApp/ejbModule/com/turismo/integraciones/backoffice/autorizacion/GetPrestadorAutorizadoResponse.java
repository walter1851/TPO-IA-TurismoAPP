
package com.turismo.integraciones.backoffice.autorizacion;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getPrestadorAutorizadoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getPrestadorAutorizadoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="solicitud" type="{http://soap.servicios/}solicitudDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPrestadorAutorizadoResponse", propOrder = {
    "solicitud"
})
public class GetPrestadorAutorizadoResponse {

    protected ResponseMensaje solicitud;
    public ResponseMensaje getSolicitud() {
        return solicitud;
    }
    public void setSolicitud(ResponseMensaje value) {
        this.solicitud = value;
    }

}
