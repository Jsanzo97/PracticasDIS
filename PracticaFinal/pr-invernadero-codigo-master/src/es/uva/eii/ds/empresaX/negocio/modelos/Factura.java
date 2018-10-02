/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class Factura {
    
    private Date fechaEmision;
    private float importe;
    private String cuentaBancaria;
    private int transferencia;
    
    public Factura(JSONObject factura){
        fechaEmision = (Date) factura.get("fechaEmision");
        importe = (Float) factura.get("importe");
        cuentaBancaria = factura.get("cuentaBancaria").toString();
        transferencia = (Integer) factura.get("EnTransferencia");
    }
    
    public boolean getTransferencia(){
        if(transferencia != 0){
            return true;
        }else{
            return false;
        }
    }
    
    public Date getFechaEmision(){
        return fechaEmision;
    }
    
    public float getImporte(){
        return importe;
    }
    
}
