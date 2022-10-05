package br.com.dh.Desafio_Spring.controller;

import br.com.dh.Desafio_Spring.dto.ProductDTO;
import br.com.dh.Desafio_Spring.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
public class ProductController {
  @Autowired
  private IProduct service;

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAll() {
    return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
  }


  @GetMapping("")
  public ResponseEntity<List<ProductDTO>> getAllByCategory(@RequestParam String category) {
    return new ResponseEntity<>(service.getAllByCategory(category), HttpStatus.OK);
  }

}
