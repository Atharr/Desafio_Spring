package br.com.dh.Desafio_Spring.controller;

import br.com.dh.Desafio_Spring.dto.ProductDTO;
import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
  @Autowired
  private IProduct service;

  @GetMapping("/articles")
  public ResponseEntity<List<ProductDTO>> getAll(@RequestParam Map<String,String> params) {
    return new ResponseEntity<>(service.getAll(params), HttpStatus.OK);
  }

  @PostMapping("/insert-articles-request")
  @ResponseBody
  public  ResponseEntity<List<ProductDTO>>save(@RequestBody List<Product> newProducts){
    return new ResponseEntity<>(service.save(newProducts), HttpStatus.CREATED);
  }



}
