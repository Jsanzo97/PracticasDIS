/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.persistencia.modeloDB;

import es.uva.eii.ds.empresaX.persistencia.ConnectionPool;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Jorge
 */
public class EmpleadoDB {
    public static JSONObject getEmpleado(String login, String password) throws UsuarioInvalidoException{
        ResultSet rsEmpleado = null;
        ResultSet rsRol = null;
        ResultSet rsDisponibilidad = null;
        ResultSet rsVinculacion = null;
        ConnectionPool conn = ConnectionPool.getInstance();
        Connection c = conn.getConnection();
        
        try{                
            PreparedStatement psEmpleado = conn.getPreparedStatement("SELECT E.NIF, E.PASSWORD, E.NOMBRE, E.FECHAINICIOENEMPRESA"
                                                                    +" FROM EMPLEADO E"
                                                                    +" WHERE E.NIF = ? AND E.PASSWORD = ? ");
            psEmpleado.setString(1,login);
            psEmpleado.setString(2,password);
            rsEmpleado = psEmpleado.executeQuery();
           
            PreparedStatement psRol = conn.getPreparedStatement("SELECT RE.ROL, RE.COMIENZOENROL"
                                                           +" FROM ROLESENEMPRESA RE "
                                                           +" WHERE RE.EMPLEADO = ? "
                                                           +" ORDER BY RE.COMIENZOENROL DESC");
            
            psRol.setString(1,login);
            rsRol = psRol.executeQuery();
            
            PreparedStatement psDisponibilidad = conn.getPreparedStatement("SELECT DE.FINALPREVISTO, DE.DISPONIBILIDAD, DE.COMIENZO"
                                                           +" FROM DISPONIBILIDADEMPLEADO DE "
                                                           +" WHERE DE.EMPLEADO = ? "
                                                           +" ORDER BY DE.COMIENZO DESC");
            psDisponibilidad.setString(1, login);
            rsDisponibilidad = psDisponibilidad.executeQuery();
            
            PreparedStatement psVinculacion= conn.getPreparedStatement("SELECT VE.VINCULO, VE.INICIO"
                                                           +" FROM VINCULACIONCONLAEMPRESA VE "
                                                           +" WHERE VE.EMPLEADO = ? "
                                                           +" ORDER BY VE.INICIO DESC");
            
            psVinculacion.setString(1,login);
            rsVinculacion = psVinculacion.executeQuery();
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        if(! (comprobarResultados(rsEmpleado, rsRol, rsDisponibilidad, rsVinculacion)) ){
                throw new UsuarioInvalidoException();
        }
        
        return procesarResultado(rsEmpleado, rsRol, rsDisponibilidad, rsVinculacion);
    }
    
    public static boolean comprobarResultados(ResultSet rsEmpleado, ResultSet rsRol, ResultSet rsDisponibilidad, ResultSet rsVinculacion){
        boolean ok = false;
        try{
            if(rsEmpleado.next() && rsRol.next() && rsDisponibilidad.next() && rsVinculacion.next()){
                ok = true;
            }
        } catch(SQLException se){
            se.printStackTrace();
        }
        return ok;
    }
    
    public static JSONObject procesarResultado(ResultSet rsEmpleado, ResultSet rsRol, ResultSet rsDisponibilidad, ResultSet rsVinculacion){
        JSONObject joEmpleado = new JSONObject();
        JSONArray joRol = new JSONArray();
        JSONArray joDisponibilidad = new JSONArray();
        JSONArray joVinculacion = new JSONArray();
        
        try{
            do{
                joEmpleado.put("dni", rsEmpleado.getString("Nif"));
                joEmpleado.put("nombre", rsEmpleado.getString("Nombre"));
                joEmpleado.put("fechaInicio", rsEmpleado.getDate("FechaInicioEnEmpresa"));
            }while(rsEmpleado.next());
                     
            do{
                JSONObject rol = new JSONObject();
                rol.put("comienzoRol", rsRol.getDate("ComienzoEnRol"));
                rol.put("tipoRol", rsRol.getString("Rol"));
                joRol.add(rol);
            }while(rsRol.next());
            
            do{
                JSONObject disponibilidad = new JSONObject();
                disponibilidad.put("comienzoDisponibilidad", rsDisponibilidad.getDate("Comienzo"));
                disponibilidad.put("finDisponibilidad", rsDisponibilidad.getDate("FinalPrevisto"));
                disponibilidad.put("tipoDisponibilidad", rsDisponibilidad.getString("Disponibilidad"));
                joDisponibilidad.add(disponibilidad);

            }while(rsDisponibilidad.next());
            
            do{
                JSONObject vinculacion = new JSONObject();
                vinculacion.put("inicioVinculacion", rsVinculacion.getDate("inicio"));
                vinculacion.put("tipoVinculo", rsVinculacion.getString("Vinculo"));
                joVinculacion.add(vinculacion);
            }while(rsVinculacion.next());
            
            joEmpleado.put("historialRol", joRol);
            joEmpleado.put("historialDisponibilidad", joDisponibilidad);
            joEmpleado.put("historialVinculacion", joVinculacion);
            
        } catch (SQLException se){
            se.printStackTrace();
        }
        return joEmpleado;
    }   
}
