/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad.ejbdinstituto.Exceptions;

/**
 *
 * @author Arsito
 */
public class NoIdException extends Exception{

    /**
     * Creates a new instance of <code>InvalidDataException</code> without
     * detail message.
     */
    public NoIdException() {
    }

    /**
     * Constructs an instance of <code>InvalidDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoIdException(String msg) {
        super(msg);
    }
}
