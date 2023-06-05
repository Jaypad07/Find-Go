package com.sei.findgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Exception thrown when information already exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException{
    public InformationExistException(String message){
        super(message);
    }
}
