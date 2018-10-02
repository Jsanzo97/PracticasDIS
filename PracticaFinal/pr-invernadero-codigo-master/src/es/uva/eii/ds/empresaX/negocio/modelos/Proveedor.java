/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class Proveedor {
    
    private String nombre;
    private String email;
    private String telefono;
    private ArrayList<PedidosAProveedor> pedidos;
    
    public Proveedor(){
        nombre = "";
        email = "";
        telefono = "";
        pedidos = null;
    }
    
    public Proveedor(JSONObject prov){
        pedidos = new ArrayList<>();
        this.nombre = prov.get("nombre").toString();
        this.email = prov.get("email").toString();
        this.telefono = prov.get("telefono").toString();
        JSONArray pedidos = (JSONArray) prov.get("pedidos");
        for(int i = 0; i < pedidos.size(); i++){
            JSONObject pedido = (JSONObject) pedidos.get(i);
            PedidosAProveedor p = new PedidosAProveedor(pedido);
            this.pedidos.add(p);
        }
    }
    
    public ArrayList<PedidosAProveedor> getPedidos(){
        return pedidos;
    }
    
    public ArrayList<PedidosAProveedor> getPedidosConFacturasPendientes(){
        ArrayList<PedidosAProveedor> pedidosDelProveedor = new ArrayList<>();
        for(int i = 0; i < pedidos.size(); i++){
            if(!pedidos.get(i).getFactura().getTransferencia()){
                pedidosDelProveedor.add(pedidos.get(i));
            }
        }
        return pedidosDelProveedor;
    }
    
    public String getNombre(){
        return nombre;
    }
}
