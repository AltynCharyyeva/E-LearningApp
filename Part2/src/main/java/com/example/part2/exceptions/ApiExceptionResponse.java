package com.example.part2.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@JsonIgnoreProperties(value = {"stackTrace", "suppressed", "cause", "localizedMessage"})
public class ApiExceptionResponse extends Exception{

    private final HttpStatus status;
    private final List<String> errors;

    @Builder
    public ApiExceptionResponse(String message, HttpStatus status, List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }
}
