package br.com.gisa.gisainfocadastro.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<List<ApiError>> handle(MethodArgumentNotValidException exception) {
        List<ApiError> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> new ApiError(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(errors, null, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    static class ApiError {
        String name;
        String message;
    }
}