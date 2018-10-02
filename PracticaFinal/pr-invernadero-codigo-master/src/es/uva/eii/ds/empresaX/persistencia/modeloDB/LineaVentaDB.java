/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia.modeloDB;

import es.uva.eii.ds.empresaX.persistencia.ConnectionPool;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class LineaVentaDB {

    public static void registrar(JSONObject linea, int idventa) {
        int cantidad = (int) linea.get("cantidad");
        String codigoProducto = (String) linea.get("codigoProducto");
        ConnectionPool conn = ConnectionPool.getInstance();
        try {
            PreparedStatement ps = conn.getPreparedStatement("INSERT INTO LineaDeVenta (cantidad, venta, producto) VALUES (?, ?, ?)");
            ps.setInt(1, cantidad);
            ps.setInt(2, idventa);
            ps.setString(3, codigoProducto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
