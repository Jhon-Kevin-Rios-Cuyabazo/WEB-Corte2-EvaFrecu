package com.uteq.inventario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("data", null);
        resp.put("message", "Error de validacion");
        
        List<Map<String, String>> errores = new ArrayList<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> f = new HashMap<>();
            f.put("field", err.getField());
            f.put("message", err.getDefaultMessage());
            errores.add(f);
        }
        resp.put("errors", errores);
        
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("data", null);
        resp.put("message", ex.getMessage());
        resp.put("meta", null);
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }
}
