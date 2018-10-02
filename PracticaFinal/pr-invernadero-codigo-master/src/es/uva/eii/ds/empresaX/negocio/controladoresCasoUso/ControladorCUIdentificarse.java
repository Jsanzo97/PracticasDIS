/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresaX.negocio.modelos.Empleado;
import es.uva.eii.ds.empresaX.serviciosComunes.Sesion;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInactivoException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;

/**
 *
 * @author Jorge
 */
public class ControladorCUIdentificarse {
    
    private Empleado empleado;
    
    public ControladorCUIdentificarse(){
        
    }
    
    public Empleado identificarEmpleado(String usuario, String password) throws UsuarioInactivoException, UsuarioInvalidoException{
        empleado = Empleado.getEmpleadoPorLoginYPassword(usuario, password);
        if(!empleado.estaActivo()){
            throw new UsuarioInactivoException();
        }else{
            Sesion s = Sesion.getInstance();
            s.setEmpleado(empleado);
        }
        return empleado;
    }
    
}
