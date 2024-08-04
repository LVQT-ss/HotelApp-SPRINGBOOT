package com.thinhle.lakesidehotel.exception;

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException (String message) {
        super(message);
    }
}
