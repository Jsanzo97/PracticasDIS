/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.noHayLotesException;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class Lote {
    
    private int id;
    private int cantidad;
    private Date fechaDeCreacion;
    private int estado;
    private String planta;

    public Lote(JSONObject joLote) {
        id = (Integer) joLote.get("id");
        cantidad = (Integer) joLote.get("cantidad");
        fechaDeCreacion = (Date) joLote.get("fechaDeCreacion");
        estado = (Integer) joLote.get("estado");
        planta = joLote.get("planta").toString();    
    }
    
    public static ArrayList<Lote> getLotes(Producto p) throws noHayLotesException{
        JSONArray joaLotes = FachadaPersistencia.getLotes(p);
        ArrayList<Lote> lotes = new ArrayList<>();
        for(int i = 0; i < joaLotes.size(); i++){
            JSONObject jo = (JSONObject) joaLotes.get(i);
            Lote l = new Lote(jo);
            lotes.add(l);
        }
        return lotes; 
    }
    
    public void setEstado(int nEstado){
        estado = nEstado;
        FachadaPersistencia.actualizaEstadoLote(this, estado);
    }
    
    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public int getEstado() {
        return estado;
    }

    public String getPlanta() {
        return planta;
    }
    
    
}
