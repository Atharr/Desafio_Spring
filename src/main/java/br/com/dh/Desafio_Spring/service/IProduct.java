package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.ProductDTO;
import br.com.dh.Desafio_Spring.dto.ProductSaveRequestDTO;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Product;

import java.util.List;
import java.util.Map;

/**
 * @interface IProduct
 * @description Interface for the Product service
 */
public interface IProduct {
  Product getProduct(String name) throws NotFoundException;
  List<ProductDTO> getAll(Map<String,String> params);
  List<ProductDTO> save(List<ProductSaveRequestDTO> product);
  Product updateOne(Long id, ProductSaveRequestDTO product);
  void delete(Long id);

}
