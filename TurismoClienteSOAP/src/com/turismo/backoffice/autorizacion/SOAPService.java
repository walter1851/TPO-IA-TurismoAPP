/**
 * SOAPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.turismo.backoffice.autorizacion;

public interface SOAPService extends javax.xml.rpc.Service {
    public java.lang.String getSOAPPortAddress();

    public com.turismo.backoffice.autorizacion.SOAP getSOAPPort() throws javax.xml.rpc.ServiceException;

    public com.turismo.backoffice.autorizacion.SOAP getSOAPPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
