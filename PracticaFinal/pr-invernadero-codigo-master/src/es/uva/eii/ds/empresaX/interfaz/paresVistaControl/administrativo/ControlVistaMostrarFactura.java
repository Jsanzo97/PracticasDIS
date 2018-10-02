/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.negocio.controladoresCasoUso.ControladorCUConsultarFacturas;
import es.uva.eii.ds.empresaX.negocio.modelos.PedidosAProveedor;
import java.util.ArrayList;


/**
 *
 * @author Jorge
 */
public class ControlVistaMostrarFactura implements ControlVista{
    
    private VistaMostrarFactura v;
    private String[] proveedor;
    private ControladorCUConsultarFacturas ccucf;
    
    public ControlVistaMostrarFactura(String[] proveedor){
        v = new VistaMostrarFactura(this);
        this.proveedor = proveedor;
        ccucf = new ControladorCUConsultarFacturas();
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
        
    }
    
    public void cargarListaPedidosConFacturas(String[] proveedores){
       ArrayList<PedidosAProveedor> pedidos = ccucf.getPedidosConFacturaDe(proveedores);
       v.cargarDesplegable(pedidos);
    }
    
    public void procesaEventoActualizarInforme(String numeroPedido){
        PedidosAProveedor p = ccucf.getPedidoConNumero(numeroPedido);
        v.mostrarInfo(p);
    }
    
}
