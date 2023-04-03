package br.com.fiap.quadro.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RuntimeException{
    public RestNotFoundException(String message) {
        super(message);
    }
}
