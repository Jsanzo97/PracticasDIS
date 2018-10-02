/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.interfaz.paresVistaControl.administrativo;

import es.uva.eii.ds.empresaX.interfaz.ControlVista;
import es.uva.eii.ds.empresaX.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresaX.negocio.controladoresCasoUso.ControladorCUConsultarFacturas;
import es.uva.eii.ds.empresaX.negocio.modelos.Proveedor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class ControlVistaConsultarFacturas implements ControlVista{
    
    private VistaConsultarFacturas v;
    private ControladorCUConsultarFacturas ccf;
    
    public ControlVistaConsultarFacturas(){
        v = new VistaConsultarFacturas(this);
        ccf = new ControladorCUConsultarFacturas();
    }
    
    public void procesarActualizarLista(){
        String opcion = v.getOpcionFechas();
        switch(opcion){
            case ("intervalo"):
                String textoInicio = v.getFechaInicio();
                String textoFin = v.getFechaFin();
                
                if(textoInicio.equals("") || textoFin.equals("")){
                    v.errorFechasVacias();
                }else{   
                    SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yy");
                    String dateActual = sm.format(new Date());
                    Date dt = null;
                    Date fechaInicio = null;
                    Date fechaFin = null;
                    try {
                        dt = sm.parse(dateActual);
                        fechaInicio = sm.parse(textoInicio);
                        fechaFin = sm.parse(textoFin);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControlVistaConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(fechaInicio.compareTo(fechaFin) > 0 ||
                        fechaInicio.compareTo(dt) > 0 ||
                        fechaFin.compareTo(dt) > 0){
                        v.errorIntervaloFechas();
                     }else{
                        ArrayList<Proveedor> proveedores = ccf.getProveedoresConFacturasIntervalo(fechaInicio, fechaFin);
                        v.mostrarProveedores(proveedores);
                    }
                }
                break;
            case("year"):
                SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yy");
                String dateActual = sm.format(new Date());
                textoInicio = "01/01/"+Calendar.getInstance().get(Calendar.YEAR);
                textoFin = "31/12/"+Calendar.getInstance().get(Calendar.YEAR);
                Date dt = null;
                Date fechaInicio = null;
                Date fechaFin = null;
                try {
                    dt = sm.parse(dateActual);
                    fechaInicio = sm.parse(textoInicio);
                    fechaFin = sm.parse(textoFin);
                } catch (ParseException ex) {
                    Logger.getLogger(ControlVistaConsultarFacturas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ArrayList<Proveedor> proveedores = ccf.getProveedoresConFacturasIntervalo(fechaInicio, fechaFin);
                v.mostrarProveedores(proveedores);
                
                break;
            case("todas"):
                proveedores = ccf.getProveedoresConFacturasTodos();
                v.mostrarProveedores(proveedores);
                break;           
        }  
    }
    
    public void procesarConsultar(){
        String proveedores = v.getProveedores();
        if(!proveedores.equals("Proveedores")){
           GestorInterfazUsuario gui = GestorInterfazUsuario.getInstance();
           gui.cargarVistaMostrarFacturasDe(proveedores);
        }
    }

    @Override
    public void hide() {
        v.ocultar();
    }

    @Override
    public void show() {
        v.mostrar();
    }
    
}
