
package com.turismo.integracion.backoffice.autorizacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPrestadorAutorizado", propOrder = {
    "id"
})
public class GetPrestadorAutorizado {

    protected int id;
    public int getId() {
        return id;
    }
    public void setId(int value) {
        this.id = value;
    }

}
