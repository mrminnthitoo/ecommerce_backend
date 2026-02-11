package com.minthitoo.ecommerce_backend.commons.response;

import com.minthitoo.ecommerce_backend.utils.enums.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class APIResponse {

    public ResponseEntity<RESTResponser> successResponse(HttpStatus statusCode, String message, Object data){
        RESTResponser restResponse = new RESTResponser();
        restResponse.setMessage(message);
        restResponse.setData(data);
        restResponse.setTimeStamp(LocalDateTime.now().toString());
        return ResponseEntity.status(statusCode).body(restResponse);
    }

    public ResponseEntity<byte[]> successImageResponse(MediaType mediaType, byte[] file){
        return ResponseEntity.ok().contentType(mediaType).body(file);
//        return ResponseEntity.status(statusCode).body(restResponse);
    }

    public ResponseEntity<RESTResponser> errorResponse(HttpStatus statusCode, String message, ErrorCodes errorCode, Object error){
        RESTResponser restResponse = new RESTResponser();
        restResponse.setMessage(message);
        restResponse.setErrorCode(errorCode);
        restResponse.setErrors(error);
        restResponse.setTimeStamp(LocalDateTime.now().toString());
        return ResponseEntity.status(statusCode).body(restResponse);
    }

}
