/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.modelos;

import es.uva.eii.ds.empresaX.persistencia.FachadaPersistencia;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ExistenciasEscasasException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.ProductoInexistenteException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class Empleado {
    
    private Date fechaInicioEmpresa;
    private String nombre;
    private String dni;
    private ArrayList<Rol> rol;
    private ArrayList<Disponibilidad> disponibilidad;
    private ArrayList<VinculacionConLaEmpresa> vinculacion;
    private Venta venta;
    
    public Empleado(){
        fechaInicioEmpresa = new Date("2018-01-01");
        nombre = "";
        dni = "";
        venta = null;
    }
    
    public Empleado(JSONObject joEmpleado){
        fechaInicioEmpresa = (Date) joEmpleado.get("fechaInicio");
        nombre = joEmpleado.get("nombre").toString();
        dni = joEmpleado.get("dni").toString();

        rol = new ArrayList<>();
        JSONArray historialRol = (JSONArray) joEmpleado.get("historialRol");
        for(int i = 0; i < historialRol.size(); i++){
            JSONObject jrol = (JSONObject) historialRol.get(i);
            Rol r = new Rol((Date)jrol.get("comienzoRol"), TipoRol.getRol(jrol.get("tipoRol").toString()));
            rol.add(r);
        }
        
        disponibilidad = new ArrayList<>();
        JSONArray historialDisponibilidad = (JSONArray) joEmpleado.get("historialDisponibilidad");
        for(int i = 0; i < historialDisponibilidad.size(); i++){
            JSONObject jDisponibilidad = (JSONObject) historialDisponibilidad.get(i);
            Disponibilidad d = new Disponibilidad((Date)jDisponibilidad.get("comienzoDisponibilidad"), (Date)jDisponibilidad.get("finDisponibilidad"), TipoDisponibilidad.getDisponibilidad(jDisponibilidad.get("tipoDisponibilidad").toString()));
            disponibilidad.add(d);
        }
        
        vinculacion = new ArrayList<>();
        JSONArray historialVinculacion = (JSONArray) joEmpleado.get("historialVinculacion");
        for(int i = 0; i < historialVinculacion.size(); i++){
            JSONObject jVinculacion = (JSONObject) historialVinculacion.get(i);
            VinculacionConLaEmpresa v = new VinculacionConLaEmpresa((Date)jVinculacion.get("inicioVinculacion"), TipoVinculacion.getVinculacion(jVinculacion.get("tipoVinculo").toString()));
            vinculacion.add(v);
        }   
    }
    
    public static Empleado getEmpleadoPorLoginYPassword(String usuario, String password) throws UsuarioInvalidoException{
        JSONObject joEmpleado = FachadaPersistencia.consultaEmpleadoPorLoginYPassword(usuario, password);
        Empleado e = new Empleado(joEmpleado);
        return e;
    }
    
    public boolean estaActivo(){
        boolean b = false;
        if(getDisponibilidad().getTipo().equals(TipoDisponibilidad.TRABAJANDO) && getVinculacion().getTipo().equals(TipoVinculacion.CONTRATADO)){
            b=true;
        }
        return b;
    }
    
    public String getDni(){
        return dni;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Date getFechaInicioEmpresa(){
        return fechaInicioEmpresa;
    }

    public Rol obtenerRolActual() {
        return rol.get(0);
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad.get(0);
    }

    public VinculacionConLaEmpresa getVinculacion() {
        return vinculacion.get(0);
    }

    public void crearVenta() {
        int id = FachadaPersistencia.getSiguienteIdVenta();
        venta = new Venta(id);
    }

    public LineaVenta nuevaLineaVenta(String codigo, Integer cantidad) throws ProductoInexistenteException, ExistenciasEscasasException {
        return venta.crearLineaVenta(codigo, cantidad);
    }

    public void eliminarUltimaVenta() {
        venta = null;
    }

    public float getTotalUltimaVenta() {
        return venta.getTotal();
    }

    public JSONObject getJsonObjectUltimaVenta() {
        JSONObject joVenta = venta.toJsonObject();
        joVenta.put("nifEmpleado", dni);
        return joVenta;
    }
}
