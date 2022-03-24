package com.obgames.obgamesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ResponseStatusException extends Exception{

    private static final long serialVersionUID = 1L;
    private String code;
    private String detail;

    public ResponseStatusException(String code, String message, String detail){
        super(message);
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

}