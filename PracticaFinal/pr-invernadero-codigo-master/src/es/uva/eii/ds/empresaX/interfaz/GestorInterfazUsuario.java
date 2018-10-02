/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz;

import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo.ControlVistaAdministrativo;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo.ControlVistaConsultarFacturas;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo.ControlVistaMostrarFactura;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.dependiente.ControlVistaDependiente;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.dependiente.CtrlVistaRegistrarVentaDirecta;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.empleado.ControlVistaidentificarse;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.operario.ControlVistaModificarLotes;
import es.uva.eii.ds.empresaX.interfaz.paresVistaControl.operario.ControlVistaOperario;
import es.uva.eii.ds.empresaX.negocio.modelos.Rol;

/**
 *
 * @author david
 */
public class GestorInterfazUsuario {
    
    private static GestorInterfazUsuario gui;
    private ControlVista estado;
    
    private GestorInterfazUsuario(){
        estado = new ControlVistaidentificarse();
    }
    
    public static GestorInterfazUsuario getInstance(){
        if(gui == null){
            return new GestorInterfazUsuario();
        }else{
            return gui;
        }
    }
    
    public void cargarVistaEmpleado(Rol rol){
        switch (rol.getTipo()) {
            case ADMINISTRATIVO:
                estado.hide();
                estado = new ControlVistaAdministrativo();
                estado.show();
                break;
            case DEPENDIENTE:
                estado.hide();
                estado = new ControlVistaDependiente();
                estado.show();
                break;
            case OPERARIO:
                //Cargar operario
                estado.hide();
                estado = new ControlVistaOperario();
                estado.show();
                break;
            case SUPERVISOR:
                //Cargar supervisor
                System.out.println("Cargar supervisor");
                break;
            default:
                break;
        }
    }
    
    public void cargarVistaConsultarFacturas(){
        estado.hide();
        estado = new ControlVistaConsultarFacturas();
        estado.show();
    }
    
    public void primerEstado(){
        estado.show();
    }
    
    public void cargarVistaMostrarFacturasDe(String proveedores){
        String prov [] = proveedores.split(",");
        estado.hide();
        ControlVistaMostrarFactura cvmf = new ControlVistaMostrarFactura(prov);
        estado = cvmf;
        cvmf.cargarListaPedidosConFacturas(prov);
        estado.show();
     }

    public void cargarVistaRegistrarVenta() {
        estado.hide();
        estado = new CtrlVistaRegistrarVentaDirecta();
        estado.show();
    }
     
    public void cargarVistaModificarLotes(){
        estado.hide();
        estado = new ControlVistaModificarLotes();
        estado.show();
    }
}
