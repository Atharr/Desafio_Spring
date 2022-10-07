package br.com.dh.Desafio_Spring.controller;

import br.com.dh.Desafio_Spring.dto.CustomerDTO;
import br.com.dh.Desafio_Spring.model.Customer;
import br.com.dh.Desafio_Spring.service.ICustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
  @Autowired
  private ICustomer service;

  @GetMapping
  public ResponseEntity<List<Customer>> getAll(@RequestParam Map<String,String> params) {
    return new ResponseEntity<>(service.getAll(params), HttpStatus.OK);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<CustomerDTO> save(@RequestBody @Valid CustomerDTO newCustomer) {
    return new ResponseEntity<>(service.save(newCustomer), HttpStatus.CREATED);
  }
}
