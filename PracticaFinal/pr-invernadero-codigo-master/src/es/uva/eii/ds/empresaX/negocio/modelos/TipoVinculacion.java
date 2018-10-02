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
public enum TipoVinculacion {
    CONTRATADO,
    DESPEDIDO,
    ENERTE;
    
    public static TipoVinculacion getVinculacion(String vinculo){
        if(vinculo.equals("1")){
            return TipoVinculacion.CONTRATADO;
        } else if (vinculo.equals("2")){
            return TipoVinculacion.DESPEDIDO;
        } else if (vinculo.equals("3")){
            return TipoVinculacion.ENERTE;
        } else{
            return null;
        }
    }
    
}
