/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia.modeloDB;

import es.uva.eii.ds.empresaX.persistencia.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class ProveedorDB {
    public static JSONArray getTodos(){
        JSONArray catalogo = new JSONArray();
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        ResultSet rsProveedor = null;
        
        try{
            PreparedStatement ps = conn.getPreparedStatement("SELECT * FROM PROVEEDOR");
            rsProveedor = ps.executeQuery();
            
            while(rsProveedor.next()){
                JSONObject prov = new JSONObject();
                prov.put("cif", rsProveedor.getString("Cif"));
                prov.put("nombre", rsProveedor.getString("Nombre"));
                prov.put("telefono", rsProveedor.getString("Telefono"));
                prov.put("email", rsProveedor.getString("Email"));
                prov.put("pedidos", PedidosAProveedorDB.getTodosDe(rsProveedor.getString("Cif")));
                catalogo.add(prov);
            }
        } catch(SQLException se){
            se.printStackTrace();
        }
        return catalogo;
    }  
}