package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.ProductDTO;
import br.com.dh.Desafio_Spring.dto.ProductSaveRequestDTO;
import br.com.dh.Desafio_Spring.exception.DuplicateException;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService implements IProduct {
  @Autowired
  private ProductRepo repo;

  private List<Product> sortList(List<Product> products, String order) {
    Comparator<Product> byName = Comparator.comparing(Product::getName);
    Comparator<Product> byPrice = Comparator.comparing(Product::getPrice);

    switch (order) {
      case "0":
        return products.stream().sorted(byName).collect(Collectors.toList());
      case "1":
        return products.stream().sorted(byName.reversed()).collect(Collectors.toList());
      case "2":
        return products.stream().sorted(byPrice).collect(Collectors.toList());
      case "3":
        return products.stream().sorted(byPrice.reversed()).collect(Collectors.toList());
    }

    return products.stream().sorted().collect(Collectors.toList());
  }

  private List<Product> filterList(List<Product> products, Map<String,String> filters) {
    Stream<Product> s = products.stream();

    for (Map.Entry<String,String> f: filters.entrySet()) {
      switch (f.getKey()) {
        case "category":
          s = s.filter(product -> product.getCategory().equalsIgnoreCase(f.getValue()));
          break;
        case "freeShipping":
          s = s.filter(product -> product.getFreeShipping() ^ f.getValue().equalsIgnoreCase("false"));
          break;
        case "prestige":
          s = s.filter(product -> product.getPrestige().equalsIgnoreCase(f.getValue()));
          break;
      }
    }

    return s.collect(Collectors.toList());
  }

  @Override
  public List<ProductDTO> getAll(Map<String,String> params) {
    List<Product> products = repo.getAll();

    if (params.containsKey("order")) {
      products = sortList(products, params.get("order"));
      params.remove("order");
    }

    if (!params.isEmpty()) {
      products = filterList(products, params);
    }

    return products.stream().map(ProductDTO::new).collect(Collectors.toList());
  }

  @Override
  public ProductDTO getProductById(Long id) throws NotFoundException {
    Optional<Product> product = repo.getById(id);

    if (product.isEmpty()) {
      throw new NotFoundException("Produto não encontrado.");
    }
    return new ProductDTO(product.get());
  }

  @Override
  public List<ProductDTO> save(List<ProductSaveRequestDTO> product) throws DuplicateException {
    int productListSize = repo.getAll().size();
    List<Product> newProducts = new ArrayList<>();


    for (ProductSaveRequestDTO p : product){
      if (repo.getProduct(p.getName()).isEmpty()) {
        continue;
      }
      if (repo.getProduct(p.getName()).get().getBrand().equals(p.getBrand())){
        throw new DuplicateException("Este produto já existe");
      }
    }

    product.stream()
            .forEach(p-> newProducts.add(new Product((long) productListSize + newProducts.size() + 1,p)));

    return repo.save(newProducts).stream()
              .map(ProductDTO::new)
              .collect(Collectors.toList());
  }

  @Override
  public Product updateOne(Long id, ProductSaveRequestDTO product)  throws NotFoundException {
    Optional <Product> productOptional = repo.getById(id);
    if(productOptional.isEmpty()) {
      throw new NotFoundException("Produto não encontrado");
    }
    return repo.updateOne(id, product);
  }

  @Override
public void delete(Long id) throws NotFoundException {
    Optional <Product> productOptional = repo.getById(id);
    if(productOptional.isEmpty()) {
      throw new NotFoundException("Produto não encontrado");
    }
  repo.delete(id);
 }
}
