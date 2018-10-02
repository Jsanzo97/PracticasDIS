/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.interfaz.GestorInterfazUsuario;

/**
 *
 * @author Jorge
 */
public class ControlVistaAdministrativo implements ControlVista {
    
    private VistaAdministrativo v;
    
    public ControlVistaAdministrativo(){
        v = new VistaAdministrativo(this);
    }
    
    public void procesarVerFacturas(){
        GestorInterfazUsuario gui = GestorInterfazUsuario.getInstance();
        gui.cargarVistaConsultarFacturas();
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
    }
}
