/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresaX.negocio.modelos.CatalogoProductosVendibles;
import es.uva.eii.ds.empresaX.negocio.modelos.Empleado;
import es.uva.eii.ds.empresaX.negocio.modelos.LineaVenta;
import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import es.uva.eii.ds.empresaX.serviciosComunes.Sesion;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoInexistenteException;
import org.json.simple.JSONObject;

/**
 *
 * @author david
 */
public class ControladorCURegistrarVentaDirecta {

    public void inicioCasoDeUso() {
        CatalogoProductosVendibles.getInstance().recargarCatalogo();
        Empleado e = Sesion.getInstance().getEmpleado();
        e.crearVenta();
    }

    public LineaVenta añadirLineaVenta(String codigo, Integer cantidad) throws ProductoInexistenteException, ExistenciasEscasasException {
        Empleado e = Sesion.getInstance().getEmpleado();
        return e.nuevaLineaVenta(codigo, cantidad);
    }

    public void cancelarCasoUso() {
        Empleado e = Sesion.getInstance().getEmpleado();
        e.eliminarUltimaVenta();
        CatalogoProductosVendibles.getInstance().recargarCatalogo();
    }

    public float getTotalVenta() {
        Empleado e = Sesion.getInstance().getEmpleado();
        return e.getTotalUltimaVenta();
    }

    public void registrarVenta() {
        Empleado e = Sesion.getInstance().getEmpleado();
        JSONObject joVenta = e.getJsonObjectUltimaVenta();
        FachadaPersistencia.registrarVenta(joVenta);
        CatalogoProductosVendibles.getInstance().actualizarEnDBExistencias();
    }
    
}
