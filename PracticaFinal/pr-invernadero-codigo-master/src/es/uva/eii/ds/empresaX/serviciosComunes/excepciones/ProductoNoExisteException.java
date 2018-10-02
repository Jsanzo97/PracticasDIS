/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresaX.serviciosComunes.excepciones;

/**
 *
 * @author Jorge
 */
public class ProductoNoExisteException extends Exception {

    /**
     * Creates a new instance of <code>ProductoNoExisteException</code> without
     * detail message.
     */
    public ProductoNoExisteException() {
    }

    /**
     * Constructs an instance of <code>ProductoNoExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProductoNoExisteException(String msg) {
        super(msg);
    }
}
