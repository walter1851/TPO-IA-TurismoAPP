
package com.turismo.integraciones.backoffice.autorizacion;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudDTO", propOrder = {
    "detalle",
    "estado",
    "id",
    "tipo"
})
public class ResponseMensaje {

    protected String detalle;
    protected String estado;
    protected int id;
    protected String tipo;

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String value) {
        this.detalle = value;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String value) {
        this.estado = value;
    }

    public int getId() {
        return id;
    }
    public void setId(int value) {
        this.id = value;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String value) {
        this.tipo = value;
    }

}
