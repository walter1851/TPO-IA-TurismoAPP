package com.turismo.backoffice.autorizacion;

public class SOAPProxy implements com.turismo.backoffice.autorizacion.SOAP {
  private String _endpoint = null;
  private com.turismo.backoffice.autorizacion.SOAP sOAP = null;
  
  public SOAPProxy() {
    _initSOAPProxy();
  }
  
  public SOAPProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPProxy();
  }
  
  private void _initSOAPProxy() {
    try {
      sOAP = (new com.turismo.backoffice.autorizacion.SOAPServiceLocator()).getSOAPPort();
      if (sOAP != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAP)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAP != null)
      ((javax.xml.rpc.Stub)sOAP)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.turismo.backoffice.autorizacion.SOAP getSOAP() {
    if (sOAP == null)
      _initSOAPProxy();
    return sOAP;
  }
  
  public boolean estaAutorizado(int nroSolicitud) throws java.rmi.RemoteException{
    if (sOAP == null)
      _initSOAPProxy();
    return sOAP.estaAutorizado(nroSolicitud);
  }
  
  public java.lang.String sayHello(java.lang.String arg0) throws java.rmi.RemoteException{
    if (sOAP == null)
      _initSOAPProxy();
    return sOAP.sayHello(arg0);
  }
  
  
}