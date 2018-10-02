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
public class ProductoInexistenteException extends Exception {

    /**
     * Creates a new instance of <code>ProductoInexistenteException</code>
     * without detail message.
     */
    public ProductoInexistenteException() {
    }

    /**
     * Constructs an instance of <code>ProductoInexistenteException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductoInexistenteException(String msg) {
        super(msg);
    }
}
