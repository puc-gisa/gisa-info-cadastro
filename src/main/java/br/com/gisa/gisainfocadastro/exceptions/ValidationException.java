package br.com.gisa.gisainfocadastro.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
