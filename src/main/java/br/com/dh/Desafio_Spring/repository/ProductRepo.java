package br.com.dh.Desafio_Spring.repository;

import br.com.dh.Desafio_Spring.dto.ProductSaveRequestDTO;
import br.com.dh.Desafio_Spring.model.Product;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
/**
 * @class ProductRepo
 * @classdesc Product repository - reads & writes product data from/to a JSON file.
 */
public class ProductRepo {
  private final String linkFile = "src/main/resources/products.json";
  ObjectMapper mapper = new ObjectMapper();

  /**
   * @name getAll
   * @description Reads all products from a JSON file and produces a list.
   * @return The product list.
   */
  public List<Product> getAll() {
    List<Product> products = null;
    try {
      products = Arrays.asList(mapper.readValue(new File(linkFile), Product[].class));
    } catch (Exception ex) {
      System.out.println("Erro ao ler o arquivo.");
    }
    return products;
  }

  /**
   * @name getProduct
   * @description Reads all products from a JSON file and finds the specified product by name.
   * @param name - the name of the product to be fetched.
   * @return The product item or an empty Optional.
   */
  public Optional<Product> getProduct(String name) {
    List<Product> products = getAll();

    for (Product p: products) {
      if (p.getName().equals(name)) {
        return Optional.of(p);
      }
    }
    return Optional.empty();
  }

  public Optional<Product> getById(Long productId) {
    List<Product> products = getAll();

    for (Product p: products) {
      if (p.getProductId().equals(productId)) {
        return Optional.of(p);
      }
    }
    return Optional.empty();
  }

  /**
   * @name save
   * @description Saves a new product in the product list file.
   * @param newProducts - the product to be saved.
   */
  public List<Product> save(List<Product> newProducts) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    List<Product> products = new ArrayList<>(getAll());
    products.addAll(newProducts);

    try {
      writer.writeValue(new File(linkFile), products);
    } catch (Exception ex) {
      System.out.println("Erro ao gravar o arquivo.");
    }
    return newProducts;
  }

  public void update(List<Product> listProductUpdated){
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    try {
      writer.writeValue(new File(linkFile), listProductUpdated);
    } catch (Exception ex) {
      System.out.println("Erro ao gravar o arquivo.");
    }
  }

  public void decreaseProductStock(Long productId, int quantinty){
    List<Product> listProducts = getAll();

    for (Product p : listProducts){
      if (p.getProductId().equals(productId)) {
        p.setQuantity(p.getQuantity() - quantinty);
      }
    }
    update(listProducts);
  }
}
