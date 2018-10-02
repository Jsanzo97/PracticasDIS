/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.empleado;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresaX.negocio.controladoresCasoUso.ControladorCUIdentificarse;
import es.uva.eii.ds.empresaX.negocio.modelos.Empleado;
import es.uva.eii.ds.empresaX.negocio.modelos.Rol;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInactivoException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;

/**
 *
 * @author Jorge
 */
public class ControlVistaidentificarse implements ControlVista{
    
    private VistaIdentificarse v;
    
    public ControlVistaidentificarse(){
        v = new VistaIdentificarse(this);
    }
    
    public void procesaEventoSalir(){
        System.exit(0);
    }
    
    public void procesaEventoInicioSesion(){
        String usuario = v.getUsuario();
        String password = v.getPassword();
        if(usuario.equals("")){
            v.errorUsuarioVacio();
        } else if(password.equals("")){
            v.errorPasswordVacio();
        } else {
            ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
            try{
                Empleado e = cui.identificarEmpleado(usuario, password);
                Rol r = e.obtenerRolActual();
                GestorInterfazUsuario gui = GestorInterfazUsuario.getInstance();
                gui.cargarVistaEmpleado(r);
            } catch (UsuarioInactivoException inactivo){
                v.errorUsuarioInactivo();
            } catch (UsuarioInvalidoException invalido){
                v.errorUsuarioInvalido();
            }
            
        }
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
