package br.com.dh.Desafio_Spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerExceptions {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException e) {
    ExceptionDetails exceptionDetails = ExceptionDetails.builder()
            .title("Objeto não encontrado")
            .message(e.getMessage())
            .status(HttpStatus.NOT_FOUND.value())
            .timeStamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateException.class)
  public ResponseEntity<ExceptionDetails> handleDuplicateException(DuplicateException e){
    ExceptionDetails exceptionDetails = ExceptionDetails.builder()
            .title("Objeto Duplicado")
            .message(e.getMessage())
            .status(HttpStatus.CONFLICT.value())
            .timeStamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(exceptionDetails, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(StockNotAvailableException.class)
  public ResponseEntity<ExceptionDetails> handleStockNotAvailableException(StockNotAvailableException e){
    ExceptionDetails exceptionDetails = ExceptionDetails.builder()
            .title("Entidade não processável")
            .message(e.getMessage())
            .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .timeStamp(LocalDateTime.now())
            .build();

    return new ResponseEntity<>(exceptionDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
