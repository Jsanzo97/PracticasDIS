/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoInexistenteException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class CatalogoProductosVendibles {
    
    private static CatalogoProductosVendibles instancia;
    private ArrayList<ProductoVendible> catalogo;
    
    private CatalogoProductosVendibles() {
        recargarCatalogo();
    }
    
    public static CatalogoProductosVendibles getInstance() {
        if (instancia == null) {
            instancia = new CatalogoProductosVendibles();
        }
        return instancia;
    }

    public void recargarCatalogo() {
        JSONArray jaPrsV = FachadaPersistencia.getTodosProductosVendibles();
        catalogo = new ArrayList<>();
        for (int i = 0; i < jaPrsV.size(); i++) {
            JSONObject joProductoV = (JSONObject) jaPrsV.get(i);
            ProductoVendible pv = new ProductoVendible(joProductoV);
            catalogo.add(pv);
        }
    }

    public ProductoVendible getProducto(String codigo) throws ProductoInexistenteException{
        for (ProductoVendible p : catalogo) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        throw new ProductoInexistenteException();
    }

    public void reducirExistenciasDe(ProductoVendible prv, Integer cantidad) throws ExistenciasEscasasException {
        for (ProductoVendible p : catalogo) {
            if (p.getCodigo().equals(prv.getCodigo())) {
                p.reducirExistencias(cantidad);
            }
        }
    }

    public void actualizarEnDBExistencias() {
        for (ProductoVendible p : catalogo) {
            JSONObject joPrv = p.toJsonObject();
            FachadaPersistencia.actualizarProducto(joPrv);
        }
    }
    
}
