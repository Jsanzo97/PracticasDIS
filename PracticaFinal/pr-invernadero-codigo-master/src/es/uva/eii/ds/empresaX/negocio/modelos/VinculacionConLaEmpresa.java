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
public class VinculacionConLaEmpresa {
    private Date fechaInicio;
    private TipoVinculacion tipo;
    
    public VinculacionConLaEmpresa(){
        fechaInicio = new Date("2018-01-01");
        tipo = TipoVinculacion.CONTRATADO;
    }
    
    public VinculacionConLaEmpresa(Date fechaInicio, TipoVinculacion tipo){
        this.fechaInicio = fechaInicio;
        this.tipo = tipo;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public TipoVinculacion getTipo() {
        return tipo;
    }
}