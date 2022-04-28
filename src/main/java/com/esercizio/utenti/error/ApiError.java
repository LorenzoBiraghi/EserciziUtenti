package com.esercizio.utenti.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ApiError {
    @Getter
    @Setter
    private HttpStatus status;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private Map<String, Object> errors;

    /**
     * Complete Constructor
     * @param status
     * @param message
     * @param errors
     */
    public ApiError(HttpStatus status, String message, Map<String, Object> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Constructor with one String
     * @param status
     * @param message
     * @param error
     */
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        HashMap<String, Object> e = new HashMap<>();
        e.put("GENERIC", error);
        errors = e;
    }

}
