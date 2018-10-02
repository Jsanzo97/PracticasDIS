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
public class noHayLotesException extends Exception {

    /**
     * Creates a new instance of <code>noHayLotesException</code> without detail
     * message.
     */
    public noHayLotesException() {
    }

    /**
     * Constructs an instance of <code>noHayLotesException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public noHayLotesException(String msg) {
        super(msg);
    }
}
