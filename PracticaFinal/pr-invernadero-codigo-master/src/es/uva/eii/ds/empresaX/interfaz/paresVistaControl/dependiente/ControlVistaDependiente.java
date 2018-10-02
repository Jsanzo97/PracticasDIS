/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.dependiente;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.interfaz.GestorInterfazUsuario;

/**
 *
 * @author david
 */
public class ControlVistaDependiente implements ControlVista {
    
    private VistaDependiente v;
    
    public ControlVistaDependiente() {
        v = new VistaDependiente(this);
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
    }

    void procesarRegistrarVenta() {
        GestorInterfazUsuario.getInstance().cargarVistaRegistrarVenta();
    }
    
    
}
