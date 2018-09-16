package com.alekseysamoylov.repair.center.model.exceptions;

/**
 * Created by alekseysamoylov on 7/31/16.
 */
public class UserRatingException extends RuntimeException {
    public UserRatingException() {
        super();
    }

    public UserRatingException(String message) {
        super(message);
    }
}
