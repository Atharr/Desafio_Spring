package br.com.dh.Desafio_Spring.controller;

import br.com.dh.Desafio_Spring.dto.ProductRequestDTO;
import br.com.dh.Desafio_Spring.model.PurchaseTicket;
import br.com.dh.Desafio_Spring.service.IPurchaseTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PurchaseTicketController {

  @Autowired
  private IPurchaseTicket service;

  @PostMapping("/purchase-request")
  @ResponseBody
  public ResponseEntity<PurchaseTicket>save(@RequestBody List<ProductRequestDTO> newPurchaseTicket) {
    return new ResponseEntity<>(service.save(newPurchaseTicket), HttpStatus.CREATED);
  }

}
