/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class ProductoVendible {
    /*Debería tener implements Producto, pero no nos ha dado tiempo a hacer bien la jerarquía de herencias
    * La idea era: 
    * -Producto abstracta, 
    * -ProductoComprable abstracta extiende Producto
    * -ProductoAuxiliar concreta implementando ProductoComprable
    * -ProductoVendible concreta implementando Producto
    * -Planta concreta implementando ProductoComprable, extendiendo de ProductoVendible
    * -Flor concreta extendiendo ProductoVendible
    */
    private String codigo;
    private String nombre;
    private String descripcion;
    private int existencias;
    private float precioventa;
    private int cantidadMinimaEnStock;
    
    public ProductoVendible(JSONObject joProducto) {
        codigo = (String)joProducto.get("codigo");
        nombre = (String) joProducto.get("nombre");
        descripcion = (String) joProducto.get("descripcion");
        existencias = (int) joProducto.get("existencias");
        precioventa = (float) joProducto.get("precioventa");
        cantidadMinimaEnStock = (int) joProducto.get("cantidadnecesaria");
    }

    public String getCodigo() {
        return codigo;
    }

    public int getExistencias() {
        return existencias;
    }

    public void reducirExistencias(Integer cantidad) throws ExistenciasEscasasException{
        if (existencias - cantidad < 0) {
            throw new ExistenciasEscasasException();
        } else {
            existencias = existencias - cantidad;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecioVenta() {
        return precioventa;
    }

    public JSONObject toJsonObject() {
        JSONObject joPrv = new JSONObject();
        joPrv.put("codigo", codigo);
        joPrv.put("nombre", nombre);
        joPrv.put("descripcion", descripcion);
        joPrv.put("existencias", existencias);
        joPrv.put("precioventa", precioventa);
        joPrv.put("cantidadnecesaria", cantidadMinimaEnStock);
        return joPrv;
    }
    
}
