package com.eazybytes.ticketing.exception;

import com.eazybytes.ticketing.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidInputException(
            final InvalidInputException e, WebRequest request) {
        ErrorResponseDto err =  new ErrorResponseDto(getTimestamp(new Date()),
                400, HttpStatus.BAD_REQUEST, e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNoFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNoFoundException(
            final ResourceNoFoundException e, WebRequest request) {
        ErrorResponseDto err =  new ErrorResponseDto(getTimestamp(new Date()),
                404, HttpStatus.NOT_FOUND, e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(
            final Exception e, WebRequest request) {
        ErrorResponseDto err =  new ErrorResponseDto(getTimestamp(new Date()),
                500, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    public String getTimestamp(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSZ");
            return formatter.format(new Date());
        }
    }







