/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresaX.negocio.modelos.Lote;
import es.uva.eii.ds.empresaX.negocio.modelos.Producto;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoNoExisteException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.noHayLotesException;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Jorge
 */
public class ControladorCUModificarLotes {

    public ControladorCUModificarLotes(){
        
    }
    
    public Producto getProducto(String nombre) throws ProductoNoExisteException, SQLException{
        Producto p = Producto.getProducto(nombre);
        return p;
    }
    
    public ArrayList<Lote> getLotes(Producto p) throws noHayLotesException{
        ArrayList<Lote> lotes = Lote.getLotes(p);
        return lotes;
    }
    
}
