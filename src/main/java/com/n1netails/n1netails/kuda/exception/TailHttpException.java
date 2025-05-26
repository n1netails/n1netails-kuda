package com.n1netails.n1netails.kuda.exception;

/**
 * Tail Http Exception
 * @author shahid foy
 */
public class TailHttpException extends Exception {

    /**
     * Tail Http Exception Constructor
     * @param message exception message
     */
    public TailHttpException(String message) { super(message); }

    /**
     * Tail Http Exception Constructor
     * @param message exception message
     * @param cause cause
     */
    public TailHttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
