/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.serviciosComunes;

import es.uva.eii.ds.empresaX.negocio.modelos.Empleado;

/**
 *
 * @author Jorge
 */
public class Sesion {
    
    private static Sesion s;
    private Empleado e;
    
    private Sesion(){
   
    }
    
    public static Sesion getInstance(){
        if(s == null){
            s = new Sesion();
        }
        return s;
        
    }
    
    public void setEmpleado(Empleado e){
        this.e = e;
    }
    
    public Empleado getEmpleado(){
        return e;
    }
    
}
