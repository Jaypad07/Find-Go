package com.sei.findgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Exception thrown when the user is not authorized to access the requested resource
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoAuthorizationException extends RuntimeException{
    String message;
    public NoAuthorizationException(String message){
        super(message);
    }
}

