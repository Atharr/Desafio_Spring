package br.com.dh.Desafio_Spring.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ServiceUnavailableException extends JsonProcessingException {
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
