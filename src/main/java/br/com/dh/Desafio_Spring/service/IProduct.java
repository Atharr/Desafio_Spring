package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Product;

import java.util.List;

/**
 * @interface IProduct
 * @description Interface for the Product service
 */
public interface IProduct {
  Product getProduct(String name) throws NotFoundException;
  List<Product> getAll();
  void save(Product newProduct);
}
