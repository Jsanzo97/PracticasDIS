/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia;

import es.uva.eii.ds.empresaX.negocio.modelos.Lote;
import es.uva.eii.ds.empresaX.negocio.modelos.Producto;
import es.uva.eii.ds.empresaX.persistencia.modeloDB.EmpleadoDB;



import es.uva.eii.ds.empresaX.persistencia.modeloDB.VentaDB;

import es.uva.eii.ds.empresaX.persistencia.modeloDB.LoteDB;
import es.uva.eii.ds.empresaX.persistencia.modeloDB.ProductoDB;
import es.uva.eii.ds.empresaX.persistencia.modeloDB.ProveedorDB;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoNoExisteException;

import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.noHayLotesException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class FachadaPersistencia {
    
    public static JSONObject consultaEmpleadoPorLoginYPassword(String usuario, String password) throws UsuarioInvalidoException{
        return EmpleadoDB.getEmpleado(usuario, password);
    }  
    
    public static JSONArray getListaProveedores(){
        return ProveedorDB.getTodos();
    }

    public static int getSiguienteIdVenta() {
        return VentaDB.getSiguienteId();
    }

    public static JSONArray getTodosProductosVendibles() {
        return ProductoDB.getTodosProductosVendibles();
    }

    public static void registrarVenta(JSONObject joVenta) {
        VentaDB.registrar(joVenta);
    }

    public static void actualizarProducto(JSONObject joPrv) {
        ProductoDB.actualizarExistenciasVendible(joPrv);
    }
    
    public static JSONObject getProducto(String nombre) throws ProductoNoExisteException, SQLException{
        return ProductoDB.getProducto(nombre);
    }
    
    public static JSONArray getLotes(Producto p) throws noHayLotesException{
        return LoteDB.getLotes(p);
    }
    
    public static void actualizaEstadoLote(Lote l, int estado){
        LoteDB.actualiza(l, estado);
    }
}
