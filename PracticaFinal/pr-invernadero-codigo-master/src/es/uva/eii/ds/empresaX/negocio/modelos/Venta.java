/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoInexistenteException;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class Venta {
    
    private int id;
    private Date fechaDeVenta;
    private ArrayList<LineaVenta> lineas;
    
    public Venta(int id) {
        this.id = id;
        fechaDeVenta = new Date();
        lineas = new ArrayList<>();
    }

    public LineaVenta crearLineaVenta(String codigo, Integer cantidad) throws ProductoInexistenteException, ExistenciasEscasasException {
        ProductoVendible prv = CatalogoProductosVendibles.getInstance().getProducto(codigo);
        CatalogoProductosVendibles.getInstance().reducirExistenciasDe(prv, cantidad);
        LineaVenta linea = new LineaVenta(prv, cantidad);
        lineas.add(linea);
        return linea;
    }

    public float getTotal() {
        float total = 0;
        for (LineaVenta lv : lineas) {
            total = total + lv.getSubtotal();
        }
        return total;
    }

    public JSONObject toJsonObject() {
        JSONObject joVenta = new JSONObject();
        JSONArray jaLineas = new JSONArray();
        joVenta.put("idventa", id);
        joVenta.put("fechaDeVenta", fechaDeVenta);
        for (LineaVenta lv : lineas) {
            JSONObject linea = lv.toJsonObject();
            jaLineas.add(linea);
        }
        joVenta.put("lineas", jaLineas);
        return joVenta;
    }
    
}
