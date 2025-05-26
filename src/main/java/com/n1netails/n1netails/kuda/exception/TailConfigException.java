package com.n1netails.n1netails.kuda.exception;

/**
 * Tail Config Exception
 * @author shahid foy
 */
public class TailConfigException extends RuntimeException {

    /**
     * Tail Config Exception Constructor
     * @param message exception message
     */
    public TailConfigException(String message) {
        super(message);
    }

    /**
     * Tail Config Exception Constructor
     * @param message exception message
     * @param cause cause
     */
    public TailConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
