/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia.modeloDB;

import es.uva.eii.ds.empresaX.persistencia.ConnectionPool;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoNoExisteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class ProductoDB {
    
    public static JSONObject getProducto(String nombre) throws ProductoNoExisteException, SQLException{
        ResultSet rsProducto = null;
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        JSONObject joProducto = null;
        
        try{
           PreparedStatement psProducto = conn.getPreparedStatement("SELECT * FROM ROOT.PRODUCTO WHERE NOMBRE = ? AND SUBTIPO = 'Planta'");
           psProducto.setString(1, nombre);
           rsProducto = psProducto.executeQuery();
           
           
           
           if(!comprobarResultado(rsProducto)){
               throw new ProductoNoExisteException();
           }else{
               joProducto = procesarResultado(rsProducto);
           }  
        } catch (SQLException se){
            se.printStackTrace();
        }       
        return joProducto;       
    }
    
    public static boolean comprobarResultado(ResultSet rsProducto) throws SQLException{
        return rsProducto.next();
    }
    
    public static JSONObject procesarResultado(ResultSet rsProducto) throws SQLException{
        JSONObject joProducto = new JSONObject();
        
        do{
            joProducto.put("codigo", rsProducto.getString("Codigo"));
            joProducto.put("nombre", rsProducto.getString("Nombre"));
            joProducto.put("descripcion", rsProducto.getString("Descripcion"));
            joProducto.put("existencias", rsProducto.getInt("Existencias"));
            joProducto.put("cantidadNecesaria", rsProducto.getInt("CantidadNecesaria"));
            joProducto.put("precioDeVenta", rsProducto.getFloat("PrecioDeVenta"));
            joProducto.put("precioDeCompra", rsProducto.getFloat("PrecioCompra"));
            joProducto.put("diasParaEntregaDelProveedor", rsProducto.getInt("DiasParaEntregaDelProveedor"));
            joProducto.put("tipoProductoAuxiliar", rsProducto.getString("TipoDeProductoAuxiliar"));
            joProducto.put("subtipo", rsProducto.getString("Subtipo"));
            joProducto.put("plantaDeLaFlor", rsProducto.getString("PlantaDeLaFlor"));
        }while(rsProducto.next());
        
        return joProducto;
    }
    
    public static JSONArray getTodosProductosVendibles() {
        ConnectionPool conn = ConnectionPool.getInstance();
        JSONArray jaProductosVendibles = new JSONArray();
        
        try {
            PreparedStatement ps = conn.getPreparedStatement("SELECT P.codigo, P.nombre, P.descripcion, P.existencias, P.preciodeventa, P.cantidadnecesaria FROM Producto P WHERE P.subtipo = ? OR P.subtipo = ?");
            ps.setString(1, "Planta");
            ps.setString(2, "Flor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int existencias = rs.getInt("existencias");
                float precioventa = rs.getFloat("preciodeventa");
                int cantidadnecesaria = rs.getInt("cantidadnecesaria");
                JSONObject joProducto = new JSONObject();
                joProducto.put("codigo", codigo);
                joProducto.put("nombre", nombre);
                joProducto.put("descripcion", descripcion);
                joProducto.put("existencias", existencias);
                joProducto.put("precioventa", precioventa);
                joProducto.put("cantidadnecesaria", cantidadnecesaria);
                jaProductosVendibles.add(joProducto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jaProductosVendibles;
    }

    public static void actualizarExistenciasVendible(JSONObject joPrv) {
        String codigo = (String) joPrv.get("codigo");
        int existencias = (int) joPrv.get("existencias");
        ConnectionPool conn = ConnectionPool.getInstance();
        try {
            PreparedStatement ps = conn.getPreparedStatement("UPDATE Producto P SET P.existencias = ? WHERE P.codigo = ?");
            ps.setInt(1, existencias);
            ps.setString(2, codigo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
