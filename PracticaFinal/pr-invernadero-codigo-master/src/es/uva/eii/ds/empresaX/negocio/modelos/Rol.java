/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import java.util.Date;

/**
 *
 * @author Jorge
 */
public class Rol {
    private Date comienzoEnRol;
    private TipoRol tipo;
    
    public Rol(){
       comienzoEnRol = new Date("2018-01-01");
       tipo = TipoRol.SUPERVISOR;
    }
    
    public Rol(Date comienzoEnRol, TipoRol tipo){
        this.comienzoEnRol = comienzoEnRol;
        this.tipo = tipo;
    }
    
    public Date getComienzoEnRol() {
        return comienzoEnRol;
    }

    public TipoRol getTipo() {
        return tipo;
    }
}