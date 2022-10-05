package br.com.dh.Desafio_Spring.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExceptionDetails {
  private String title;
  private int status;
  private String message;
  private LocalDateTime timeStamp;
}
