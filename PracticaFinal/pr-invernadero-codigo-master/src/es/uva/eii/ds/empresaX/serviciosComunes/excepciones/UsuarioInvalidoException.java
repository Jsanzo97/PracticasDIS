/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.serviciosComunes.excepciones;

/**
 *
 * @author david
 */
public class UsuarioInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>ExceptionGeneral</code> without detail
     * message.
     */
    public UsuarioInvalidoException() {
    }

    /**
     * Constructs an instance of <code>ExceptionGeneral</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioInvalidoException(String msg) {
        super(msg);
    }
}
