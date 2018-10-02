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
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class FacturaDB {
    
    public static JSONObject getFactura(int numPedido) {
        JSONObject factura = new JSONObject();
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        ResultSet rsFactura = null;
        
        try{
            PreparedStatement ps = conn.getPreparedStatement("SELECT * FROM FACTURA F WHERE F.PEDIDO = ?");
            ps.setInt(1,numPedido);
            rsFactura = ps.executeQuery();
            
            while(rsFactura.next()){
                factura.put("id", rsFactura.getInt("Id"));
                factura.put("fechaEmision", rsFactura.getDate("FechaDeEmision"));
                factura.put("importe", rsFactura.getFloat("Importe"));
                factura.put("cuentaBancaria", rsFactura.getString("CuentaBancaria"));
                factura.put("EnTransferencia", rsFactura.getInt("EnTransferencia"));
            }
            
        } catch(SQLException se){
            se.printStackTrace();
        }
        
        return factura;
    }
    
}
