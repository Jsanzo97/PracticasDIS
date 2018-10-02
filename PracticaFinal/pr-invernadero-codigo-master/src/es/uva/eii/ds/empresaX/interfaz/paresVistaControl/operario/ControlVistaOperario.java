/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.operario;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.interfaz.GestorInterfazUsuario;

/**
 *
 * @author Jorge
 */
public class ControlVistaOperario implements ControlVista{
    
    private VistaOperario v;
    
    public ControlVistaOperario(){
        v = new VistaOperario(this);
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
    }
    
    public void procesarModificarLotes(){
        GestorInterfazUsuario gui = GestorInterfazUsuario.getInstance();
        gui.cargarVistaModificarLotes();
    }
    
}
