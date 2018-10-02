/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class PedidosAProveedor {
    
    private int numeroDePedido;
    private Date fechaDeRealizacion; 
    private String estaPendiente;
    private Factura factura;
    
    public PedidosAProveedor(JSONObject pedido){
        numeroDePedido = (Integer) pedido.get("numeroPedido");
        fechaDeRealizacion = (Date) pedido.get("fechaRealizacion");
        estaPendiente = pedido.get("estaPendiente").toString();
        factura = new Factura((JSONObject)pedido.get("factura"));
    }
    
    public Factura getFactura() {
        return factura;
    }
    
    public int getNumeroPedido(){
        return numeroDePedido;
    }
    
    public Date getFechaRealizacionPedido(){
        return fechaDeRealizacion;
    }
}
