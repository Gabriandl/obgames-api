package com.obgames.obgamesapi.exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String code;

    public ErrorDetails(String code, Date timestamp, String message, String details) {
        super();
        this.code = code;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
