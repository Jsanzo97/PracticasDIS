/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.operario;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.negocio.controladoresCasoUso.ControladorCUModificarLotes;
import es.uva.eii.ds.empresaX.negocio.modelos.Lote;
import es.uva.eii.ds.empresaX.negocio.modelos.Producto;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoNoExisteException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.noHayLotesException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jorge
 */
public class ControlVistaModificarLotes implements ControlVista{
    
    private VistaModificarLotes v;
    
    public ControlVistaModificarLotes(){
        v = new VistaModificarLotes(this);
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
    }
    
    public void procesaBuscarPlanta() {
        String nombre = v.getNombrePlanta();
        if(nombre.equals("")){
            v.nombreVacio();
        } else{
            ControladorCUModificarLotes cuml = new ControladorCUModificarLotes();
            try {
                Producto p = cuml.getProducto(nombre);
                ArrayList<Lote> lotes = cuml.getLotes(p);
                v.mostrarLotes(lotes);
            } catch (ProductoNoExisteException ex) {
                v.errorProductoNoExiste();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (noHayLotesException ex) {
                v.errorNoHayLotes();
            }
        }
    } 
    
    public void procesaEventoActualizarLote(){
        Lote l = v.getLoteSeleccionado();
        String nuevoEstado = v.getNuevoEstado();
        if(nuevoEstado.equals("")){
            v.estadoVacio();
        }else{
            int nEstado = Integer.parseInt(nuevoEstado);
            if(nEstado < 0 || nEstado > 6){
                v.estadoErroneo();              
            }else{
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Esta seguro de que desea cambiar el estado del lote?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                       l.setEstado(nEstado);
                       v.actualizadoConExito(nEstado);
                }            
            }
        }
    }
}