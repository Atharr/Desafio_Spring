package br.com.dh.Desafio_Spring.exception;

/**
 * @class NotFoundException
 * @classdesc Custom exception for not found events.
 */
public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
