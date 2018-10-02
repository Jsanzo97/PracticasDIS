/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class LineaVenta {
    
    private int cantidad;
    private ProductoVendible producto;
    
    public LineaVenta(ProductoVendible pr, int cantidad) {
        producto = pr;
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return producto.getNombre();
    }

    public float getPrecioVentaProducto() {
        return producto.getPrecioVenta();
    }

    public int getCantidad() {
        return cantidad;
    }

    public float getSubtotal() {
        return producto.getPrecioVenta() * cantidad;
    }
    
    public JSONObject toJsonObject() {
        JSONObject joLinea = new JSONObject();
        joLinea.put("cantidad", cantidad);
        joLinea.put("codigoProducto", producto.getCodigo());
        return joLinea;
    }
    
}
