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
public class ExistenciasEscasasException extends Exception {

    /**
     * Creates a new instance of <code>ExistenciasEscasasException</code>
     * without detail message.
     */
    public ExistenciasEscasasException() {
    }

    /**
     * Constructs an instance of <code>ExistenciasEscasasException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExistenciasEscasasException(String msg) {
        super(msg);
    }
}
