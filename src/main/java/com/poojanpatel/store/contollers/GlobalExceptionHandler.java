package com.poojanpatel.store.contollers;

import com.poojanpatel.store.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.UnrecoverableEntryException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UnrecoverableEntryException.class)
    public ResponseEntity<ErrorDto> handleUnreadableException(){

        return ResponseEntity.badRequest().body(
                new ErrorDto("Unreadable Exception")
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(MethodArgumentNotValidException ex){
        var errors = new HashMap<String,String>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()))
        ;

        return ResponseEntity.badRequest().body(errors);
    }

}
