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
public class Disponibilidad {
    
    private Date comienzo;
    private Date finPrevisto;
    private TipoDisponibilidad tipo;
    
    public Disponibilidad(){
        comienzo = new Date("2018-01-01");
        finPrevisto = new Date("2018-01-01");
        tipo = TipoDisponibilidad.TRABAJANDO;
    }
    
    public Disponibilidad(Date comienzo, Date finPrevisto, TipoDisponibilidad tipo){
        this.comienzo = comienzo;
        this.finPrevisto = finPrevisto;
        this.tipo = tipo;
    }
    
    public Date getComienzo() {
        return comienzo;
    }

    public Date getFinPrevisto() {
        return finPrevisto;
    }
    
    public TipoDisponibilidad getTipo() {
        return tipo;
    }
}