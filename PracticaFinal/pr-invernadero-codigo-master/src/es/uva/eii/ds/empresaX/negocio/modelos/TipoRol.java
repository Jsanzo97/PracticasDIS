/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

/**
 *
 * @author Jorge
 */
public enum TipoRol {
    SUPERVISOR,
    ADMINISTRATIVO,
    OPERARIO,
    DEPENDIENTE;
    
    public static TipoRol getRol(String rol){
        if(rol.equals("1")){
            return TipoRol.SUPERVISOR;
        } else if (rol.equals("2")){
            return TipoRol.ADMINISTRATIVO;
        } else if (rol.equals("3")){
            return TipoRol.OPERARIO;
        } else if (rol.equals("4")){
            return TipoRol.DEPENDIENTE;
        } else {
            return null;
        }
    }
    
}


