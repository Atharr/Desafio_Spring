package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.ProductDTO;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProduct {
  @Autowired
  private ProductRepo repo;

  @Override
  public List<ProductDTO> getAll() {
    return repo.getAll().stream()
            .map(ProductDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public Product getProduct(String name) throws NotFoundException {
    Optional<Product> product = repo.getProduct(name);

    if (product.isEmpty()) {
      throw new NotFoundException("Produto não encontrado.");
    }
    return product.get();
  }

  @Override
  public void save(Product product) {
    repo.save(product);
  }

  @Override
  public List<ProductDTO> getAllByCategory(String category) {
    return repo.getAllByCategory(category).stream()
            .map(ProductDTO::new)
            .collect(Collectors.toList());
  }
}
