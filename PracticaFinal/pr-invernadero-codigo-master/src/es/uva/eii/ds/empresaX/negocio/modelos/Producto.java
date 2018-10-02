/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoNoExisteException;
import java.sql.SQLException;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class Producto {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private int existencias;
    private int cantidadNecesaria;
    private float precioDeVenta;
    private float precioDeCompra;
    private int diasParaEntregaDelProveedor;
    private String tipoDeProductoAuxiliar;
    private String subtipo;
    private String plantaDeLaFlor;
    
    
    public Producto(JSONObject joProducto){
        codigo = joProducto.get("codigo").toString();
        nombre = joProducto.get("nombre").toString();
        descripcion = joProducto.get("descripcion").toString();
        existencias = (Integer)joProducto.get("existencias");
        cantidadNecesaria = (Integer) joProducto.get("cantidadNecesaria");
        precioDeVenta = (Float)joProducto.get("precioDeVenta");
        precioDeCompra = (Float)joProducto.get("precioDeCompra");
        diasParaEntregaDelProveedor = (Integer)joProducto.get("diasParaEntregaDelProveedor");
        tipoDeProductoAuxiliar = (String) joProducto.get("tipoProductoAuxiliar");
        subtipo = joProducto.get("subtipo").toString();
        plantaDeLaFlor =(String) joProducto.get("plantaDeLaFlor");
    }
    
    public static Producto getProducto(String nombre) throws ProductoNoExisteException, SQLException{
        JSONObject joProducto = FachadaPersistencia.getProducto(nombre);
        Producto p = new Producto(joProducto);
        return p;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public float getPrecioDeVenta() {
        return precioDeVenta;
    }

    public float getPrecioDeCompra() {
        return precioDeCompra;
    }

    public int getDiasParaEntregaDelProveedor() {
        return diasParaEntregaDelProveedor;
    }

    public String getTipoDeProductoAuxiliar() {
        return tipoDeProductoAuxiliar;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public String getPlantaDeLaFlor() {
        return plantaDeLaFlor;
    }
    
    
    
}
