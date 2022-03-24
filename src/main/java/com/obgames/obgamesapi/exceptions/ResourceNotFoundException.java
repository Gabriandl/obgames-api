package com.obgames.obgamesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;
    private String code;

    public ResourceNotFoundException(String code, String message){
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
