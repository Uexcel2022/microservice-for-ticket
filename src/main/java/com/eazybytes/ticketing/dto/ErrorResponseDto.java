package com.eazybytes.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
    private String timestamp;
    private int status;
    private HttpStatus error;
    private String message;
    private String apiPath;
}
