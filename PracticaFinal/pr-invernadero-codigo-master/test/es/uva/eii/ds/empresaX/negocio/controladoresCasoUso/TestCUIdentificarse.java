/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresaX.negocio.modelos.Empleado;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInvalidoException;
import es.uva.eii.ds.empresaX.serviciosComunes.excepciones.UsuarioInactivoException;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class TestCUIdentificarse {
    
    @Test
    public void testFlujoNormal() throws UsuarioInactivoException, UsuarioInvalidoException {
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000023", "alejandra");
        assertEquals(e.getDni(), "000000023");
        assertEquals(e.getNombre(), "alejandra");
        assertEquals(e.getFechaInicioEmpresa(), "2018-05-09");
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testNifIncorrecto() throws UsuarioInactivoException, UsuarioInvalidoException {
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("111111111", "alejandra");
    }
    
    @Test(expected = UsuarioInvalidoException.class)
    public void testPassIncorrecta() throws UsuarioInactivoException, UsuarioInvalidoException {
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000023", "alejandrA");
    }
    
    @Test(expected = UsuarioInactivoException.class)
    public void testUsuarioDespedido() throws UsuarioInactivoException, UsuarioInvalidoException {
        //Despedido y trabajando
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000037", "antia");
    }
    
    @Test(expected = UsuarioInactivoException.class)
    public void testUsuarioEnERTE() throws UsuarioInactivoException, UsuarioInvalidoException {
        //Despedido y trabajando
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000002", "luis");
    }
    
    @Test(expected = UsuarioInactivoException.class)
    public void testUsuarioVacaciones() throws UsuarioInactivoException, UsuarioInvalidoException {
        //Contratado y vacaciones
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000001", "juan");
    }
    
    @Test(expected = UsuarioInactivoException.class)
    public void testUsuarioBajaTemporal() throws UsuarioInactivoException, UsuarioInvalidoException {
        //Contratado y vacaciones
        ControladorCUIdentificarse cui = new ControladorCUIdentificarse();
        Empleado e = cui.identificarEmpleado("000000011", "ivan");
    }
}
