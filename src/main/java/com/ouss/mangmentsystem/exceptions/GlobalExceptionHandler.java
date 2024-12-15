package com.ouss.mangmentsystem.exceptions;

import com.ouss.mangmentsystem.DTO.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Project Name: MangmentSystem
 * File Name: GlobalExceptionHandler
 * Created by: DELL
 * Created on: 12/14/2024
 * Description:
 * <p>
 * GlobalExceptionHandler is a part of the MangmentSystem project.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response>handleException(Exception e){
        Response response = Response.builder()
                 .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                 .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response>handleNotFoundException(NotFoundException e){
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameValueRequiredException.class)
    public ResponseEntity<Response>handleNameValueRequiredException(NameValueRequiredException e){
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response>handleInvalidCredentialsException (InvalidCredentialsException e){
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
