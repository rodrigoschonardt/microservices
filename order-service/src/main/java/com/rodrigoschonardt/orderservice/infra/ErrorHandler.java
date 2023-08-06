package com.rodrigoschonardt.orderservice.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler
{
    @ExceptionHandler( EntityNotFoundException.class )
    public ResponseEntity handleNotFound()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity handleMethodArgumentNotValid( MethodArgumentNotValidException ex )
    {
        List<FieldError> errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body( errors.stream().map( ValidationErrorData :: new ).toList() );
    }

    private record ValidationErrorData( String field, String message )
    {
        public ValidationErrorData( FieldError error )
        {
            this( error.getField(), error.getDefaultMessage() );
        }
    }
}