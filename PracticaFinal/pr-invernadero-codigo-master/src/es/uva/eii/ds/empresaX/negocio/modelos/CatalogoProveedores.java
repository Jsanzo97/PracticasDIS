/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class CatalogoProveedores {
    
    private static CatalogoProveedores cp;
    private static ArrayList<Proveedor> catalogo;
    
    private CatalogoProveedores(){
        catalogo = new ArrayList();
        JSONArray info = FachadaPersistencia.getListaProveedores();
        for(int i = 0; i < info.size(); i++){
            JSONObject prov = (JSONObject) info.get(i);
            Proveedor p = new Proveedor(prov);
            catalogo.add(p);
        }
    }
    
    public static CatalogoProveedores getInstance(){
        if (cp == null) {
            cp = new CatalogoProveedores();
        }
        return cp;
    }   
    
    public ArrayList<Proveedor> getCatalogo(){
        return catalogo;
    }
    
    public ArrayList<Proveedor> getProveedoresIntervalo(Date inicio, Date fin){
        ArrayList<Proveedor> p = new ArrayList<>();
        boolean add = false;
        for(int i = 0; i < catalogo.size(); i++){
           ArrayList<PedidosAProveedor> pedidos = catalogo.get(i).getPedidos();
           for(int j = 0; j < pedidos.size(); j++){
               Factura f = pedidos.get(j).getFactura();
               if(!f.getTransferencia() && f.getFechaEmision().compareTo(inicio) >= 0 && f.getFechaEmision().compareTo(fin) <=0){
                   add = true;
                   break;
               }
           }
           if(add){
               p.add(catalogo.get(i));
           }
           add = false;
        }        
        return p;
    }
    
    public ArrayList<Proveedor> getTodos(){
        ArrayList<Proveedor> p = new ArrayList<>();
        boolean add = false;
        for(int i = 0; i < catalogo.size(); i++){
           ArrayList<PedidosAProveedor> pedidos = catalogo.get(i).getPedidos();
           for(int j = 0; j < pedidos.size(); j++){
               Factura f = pedidos.get(j).getFactura();
               if(!f.getTransferencia()){
                   add = true;
                   break;
               }
           }
           if(add){
               p.add(catalogo.get(i));
           }
           add = false;
        }        
        return p;
    }
    
    public Proveedor getProveedor(String name){
        Proveedor p = null;
        for(int i = 0; i < catalogo.size(); i++){
            if(catalogo.get(i).getNombre().equals(name)){
                p = catalogo.get(i);
                break;
            }
        }
        return p;
    }
    
    public Proveedor getProveedorNum(int numPedido){
        Proveedor p = null;
        for(int i = 0; i < catalogo.size(); i++){
            ArrayList<PedidosAProveedor> ptes = catalogo.get(i).getPedidosConFacturasPendientes();
            for(int j = 0; j < ptes.size(); j++){
                if(ptes.get(j).getNumeroPedido() == numPedido){
                    p = catalogo.get(i);
                    break;
                }
            }
        }
        return p;
    }
}