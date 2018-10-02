/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia.modeloDB;

import es.uva.eii.ds.empresaX.negocio.modelos.Lote;
import es.uva.eii.ds.empresaX.negocio.modelos.Producto;
import es.uva.eii.ds.empresaX.persistencia.ConnectionPool;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.noHayLotesException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class LoteDB {
    
    public static JSONArray getLotes(Producto p) throws noHayLotesException{
        ResultSet rsLotes = null;
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        JSONObject joLote = null;
        JSONArray joaLotes = null;
        
        try{
           PreparedStatement psLotes = conn.getPreparedStatement("SELECT * FROM ROOT.LOTE WHERE PLANTA = ? ");
           psLotes.setString(1, p.getCodigo());
           rsLotes = psLotes.executeQuery();
      
           if(!comprobarResultado(rsLotes)){
               throw new noHayLotesException();
           }else{
               joaLotes = procesarResultado(rsLotes);
           }  
        } catch (SQLException se){
            se.printStackTrace();
        }       
        return joaLotes;
    }
    
    public static boolean comprobarResultado(ResultSet rsLotes) throws SQLException{
        return rsLotes.next();
    }
    
    public static JSONArray procesarResultado(ResultSet rsLotes) throws SQLException{
        JSONArray joaLotes = new JSONArray();
        
        do{
            JSONObject jo = new JSONObject();
            jo.put("id", rsLotes.getInt("Id"));
            jo.put("cantidad", rsLotes.getInt("Cantidad"));
            jo.put("fechaDeCreacion", rsLotes.getDate("FechaDeCreacion"));
            jo.put("estado", rsLotes.getInt("Estado"));
            jo.put("planta", rsLotes.getString("Planta"));
            joaLotes.add(jo);
        }while(rsLotes.next());
        
        return joaLotes;
    }
    
    public static void actualiza(Lote l, int estado){
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        
        try {
            PreparedStatement psLotes = conn.getPreparedStatement("UPDATE LOTE L SET L.ESTADO = ? WHERE L.ID = ?");
            psLotes.setInt(1, estado);
            psLotes.setInt(2, l.getId());
            psLotes.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}