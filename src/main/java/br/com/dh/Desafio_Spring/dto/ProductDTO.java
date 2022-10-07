package br.com.dh.Desafio_Spring.dto;

import br.com.dh.Desafio_Spring.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
/**
 * @class ProductDTO
 * @classdesc Data Transfer Object for the Product class
 */
public class ProductDTO {
  private Long productId;
  private String name;
  private Integer quantity;
  private BigDecimal price;

  /**
   * Receives a single Product object and assigns the productId, name and quantity properties.
   * @param product - the Product object to convert
   */
  public ProductDTO(Product product) {
    this.productId = product.getProductId();
    this.name = product.getName();
    this.quantity = product.getQuantity();
    this.price = product.getPrice();
  }
}
