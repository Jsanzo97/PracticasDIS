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
public enum TipoDisponibilidad {
    VACACIONES,
    BAJATEMPORAL,
    TRABAJANDO;
    
    public static TipoDisponibilidad getDisponibilidad(String disponibilidad){
        if(disponibilidad.equals("1")){
            return TipoDisponibilidad.VACACIONES;
        } else if (disponibilidad.equals("2")){
            return TipoDisponibilidad.BAJATEMPORAL;
        } else if (disponibilidad.equals("3")){
            return TipoDisponibilidad.TRABAJANDO;
        } else{
            return null;
        }
    }
    
}
