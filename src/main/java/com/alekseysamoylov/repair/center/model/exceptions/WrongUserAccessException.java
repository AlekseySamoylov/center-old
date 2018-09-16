package com.alekseysamoylov.repair.center.model.exceptions;

/**
 * Created by alekseysamoylov on 7/29/16.
 */
public class WrongUserAccessException extends RuntimeException {
    public WrongUserAccessException() {
        super();
    }

    public WrongUserAccessException(String message) {
        super(message);
    }
}
