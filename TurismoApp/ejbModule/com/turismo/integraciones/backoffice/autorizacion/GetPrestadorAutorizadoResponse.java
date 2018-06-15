
package com.turismo.integraciones.backoffice.autorizacion;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
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
