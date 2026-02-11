package com.minthitoo.ecommerce_backend.commons.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class NotFoundException extends Exception{

    private Map<String, String> errors = new HashMap<>();

    public NotFoundException(String message, String key, String error){
        super(message);
        this.errors.put(key, error);
    }

}
