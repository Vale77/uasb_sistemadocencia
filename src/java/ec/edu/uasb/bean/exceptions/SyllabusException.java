/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.bean.exceptions;

/**
 *
 * @author victor.barba
 */
public class SyllabusException extends Exception {

    /**
     * Creates a new instance of
     * <code>SyllabusException</code> without detail message.
     */
    public SyllabusException() {
    }

    /**
     * Constructs an instance of
     * <code>SyllabusException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SyllabusException(String msg) {
        super(msg);
    }
}
