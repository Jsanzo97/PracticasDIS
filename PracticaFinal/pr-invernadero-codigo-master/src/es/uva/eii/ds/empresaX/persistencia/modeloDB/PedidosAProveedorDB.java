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
public class PedidosAProveedorDB {
    
    public static JSONArray getTodosDe(String cif){
        JSONArray pedidos = new JSONArray();
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        ResultSet rsPedidos = null;
        try{
            PreparedStatement ps = conn.getPreparedStatement("SELECT * FROM PEDIDOAPROVEEDOR P WHERE P.PROVEEDOR = ? ");
            ps.setString(1, cif);
            rsPedidos = ps.executeQuery();
            
            while(rsPedidos.next()){
                JSONObject pedido = new JSONObject();
                pedido.put("numeroPedido", rsPedidos.getInt("NumeroDePedido"));
                pedido.put("fechaRealizacion", rsPedidos.getDate("FechaDeRealizacion"));
                pedido.put("estaPendiente", rsPedidos.getString("EstaPendiente"));
                pedido.put("factura", FacturaDB.getFactura(rsPedidos.getInt("NumeroDePedido")));
                pedidos.add(pedido);
            }
            
        } catch(SQLException se){
            se.printStackTrace();
        }
        
        return pedidos;
    }
    
}
