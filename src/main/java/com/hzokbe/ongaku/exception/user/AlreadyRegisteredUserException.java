package com.hzokbe.ongaku.exception.user;

public class AlreadyRegisteredUserException extends RuntimeException {
    public AlreadyRegisteredUserException(String message) {
        super(message);
    }
}
