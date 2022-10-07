package br.com.dh.Desafio_Spring.exception;

public class StockNotAvailableException extends RuntimeException{
    public StockNotAvailableException(String message) {
        super(message);
    }
}
