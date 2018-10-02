/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresaX.negocio.modelos.CatalogoProveedores;
import es.uva.eii.ds.empresaX.negocio.modelos.PedidosAProveedor;
import es.uva.eii.ds.empresaX.negocio.modelos.Proveedor;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jorge
 */
public class ControladorCUConsultarFacturas {
    
    private CatalogoProveedores cp;
    private ArrayList<PedidosAProveedor> pendientes;
    
    public ControladorCUConsultarFacturas(){
        pendientes = new ArrayList<>();
    }
    
    public ArrayList<Proveedor> getProveedoresConFacturasIntervalo(Date inicio, Date fin){
        cp = CatalogoProveedores.getInstance();
        ArrayList<Proveedor> proveedores = cp.getProveedoresIntervalo(inicio, fin);
        return proveedores;
    }
    
    public ArrayList<Proveedor> getProveedoresConFacturasTodos(){
        cp = CatalogoProveedores.getInstance();
        ArrayList<Proveedor> proveedores = cp.getTodos();
        return proveedores;
    }
    
    public ArrayList<Proveedor> getProveedoresConFacturasYear(Date inicio, Date fin){
        cp = CatalogoProveedores.getInstance();
        ArrayList<Proveedor> proveedores = cp.getProveedoresIntervalo(inicio, fin);
        return proveedores;
    }
    
    public ArrayList<PedidosAProveedor> getPedidosConFacturaDe(String[] proveedores){
        ArrayList<PedidosAProveedor> pedidosTotales = new ArrayList<>();
        CatalogoProveedores cp = CatalogoProveedores.getInstance();
        for(int i = 0; i < proveedores.length; i++){
            Proveedor p = cp.getProveedor(proveedores[i]);
            ArrayList<PedidosAProveedor> pedidos = p.getPedidosConFacturasPendientes();
            for(int j = 0; j < pedidos.size(); j++){
                pedidosTotales.add(pedidos.get(j));
                pendientes.add(pedidos.get(j));
            }
        }
        return pedidosTotales;
    }
    
    public PedidosAProveedor getPedidoConNumero(String numero){
        PedidosAProveedor p = null;
        int index = Integer.parseInt(numero);
        for(int i = 0; i < pendientes.size(); i++){
            if(pendientes.get(i).getNumeroPedido() == index){
                p = pendientes.get(i);
                break;
            }
        }
        return p;
    }
    
    
}
