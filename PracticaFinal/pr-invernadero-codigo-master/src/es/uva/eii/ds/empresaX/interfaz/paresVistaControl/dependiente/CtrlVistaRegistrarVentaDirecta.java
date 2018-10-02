/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.dependiente;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.negocio.controladoresCasoUso.ControladorCURegistrarVentaDirecta;
import es.uva.eii.ds.empresaX.negocio.modelos.LineaVenta;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoInexistenteException;

/**
 *
 * @author david
 */
public class CtrlVistaRegistrarVentaDirecta implements ControlVista {
    
    private VistaRegistrarVentaDirecta v;
    private ControladorCURegistrarVentaDirecta ctrlCU;
    
    public CtrlVistaRegistrarVentaDirecta() {
        v = new VistaRegistrarVentaDirecta(this);
        ctrlCU = new ControladorCURegistrarVentaDirecta();
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
        ctrlCU.inicioCasoDeUso();
    }

    public void procesarAñadirLinea() {
        String codigo = v.getCodigoProducto();
        String cantidad = v.getCantidad();
        if (codigo == "" || codigo == null || cantidad == "" || cantidad == null || Integer.valueOf(cantidad) < 1) {
            v.mostrarErrorCamposVacios();
        } else {
            try {
                LineaVenta linea = ctrlCU.añadirLineaVenta(codigo, Integer.valueOf(cantidad));
                String nombreProducto = linea.getNombreProducto();
                float precio = linea.getPrecioVentaProducto();
                int cantidadlinea = linea.getCantidad();
                v.mostrarNombreProducto(nombreProducto);
                v.mostrarPrecioVenta(precio);
                v.mostrarSubtotal(precio * cantidadlinea);
                v.permitirNuevaLineaYFinalizar();
            } catch (ProductoInexistenteException pi) {
                v.mostrarErrorNoExisteProducto();
            } catch (ExistenciasEscasasException ee) {
                v.mostrarErrorExistenciasEscasas();
            }
        }
    }

    public void procesarEventoSalir() {
        ctrlCU.cancelarCasoUso();
        v.ocultar();
    }

    public void procesarSinFactura() {
        float total = ctrlCU.getTotalVenta();
        v.mostrarTotal(total);
        v.mostrarTramitarPago();
        ctrlCU.registrarVenta();
    }

    public void procesarConFactura() {
        float total = ctrlCU.getTotalVenta();
        v.mostrarTotal(total);
        v.mostrarTramitarPago();
        v.mostrarGenerarFactura();
        ctrlCU.registrarVenta();
    }
    
}
