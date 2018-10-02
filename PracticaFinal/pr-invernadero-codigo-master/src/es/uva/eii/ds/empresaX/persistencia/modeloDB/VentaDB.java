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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class VentaDB {

    public static int getSiguienteId() {
        ConnectionPool conn = ConnectionPool.getInstance();
        int id = 0;
        try {
            PreparedStatement ps = conn.getPreparedStatement("SELECT MAX(V.iddeventa) AS ultimoId FROM Venta V");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ultimoId");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id+1;
    }

    public static void registrar(JSONObject joVenta) {
        int idventa = (int) joVenta.get("idventa");
        Date fecha = (Date) joVenta.get("fechaDeVenta");
        String nifDependiente = (String) joVenta.get("nifEmpleado");
        JSONArray lineas = (JSONArray) joVenta.get("lineas");
        ConnectionPool conn = ConnectionPool.getInstance();
        try {
            PreparedStatement ps = conn.getPreparedStatement("INSERT INTO Venta (iddeventa, fechadeventa, dependiente) VALUES (?, ?, ?)");
            ps.setInt(1, idventa);
            ps.setDate(2, new java.sql.Date(fecha.getTime()));
            ps.setString(3, nifDependiente);
            ps.executeUpdate();
            for (int i = 0; i < lineas.size(); i++) {
                LineaVentaDB.registrar((JSONObject)lineas.get(i), idventa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
