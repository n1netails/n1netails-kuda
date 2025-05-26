package com.n1netails.n1netails.kuda.exception;

import lombok.Getter;

/**
 * Tail Http Exception
 * @author shahid foy
 */
@Getter
public class TailHttpException extends Exception {

    /**
     * Http status code
     */
    private final int statusCode;

    /**
     * Tail Http Exception Constructor
     * @param message exception message
     * @param statusCode status code
     */
    public TailHttpException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
