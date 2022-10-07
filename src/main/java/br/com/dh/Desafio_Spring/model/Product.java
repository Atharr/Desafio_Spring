package br.com.dh.Desafio_Spring.model;

import br.com.dh.Desafio_Spring.dto.ProductSaveRequestDTO;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * @class Product
 * @classdesc Product model - represents a product and its properties.
 * @implements Comparable<Product>
 */
public class Product implements Comparable<Product> {
  private Long productId;
  private String name;
  private String category;
  private String brand;
  private BigDecimal price;
  private Integer quantity;
  private Boolean freeShipping;
  private String prestige;

  public Product(Long i, ProductSaveRequestDTO p) {
    this.productId = i;
    this.name = p.getName();
    this.category = p.getCategory();
    this.brand = p.getBrand();
    this.price = p.getPrice();
    this.quantity = p.getQuantity();
    this.freeShipping = p.getFreeShipping();
    this.prestige = p.getPrestige();
  }
  @Override
  public int compareTo(Product other) {
    return this.getName().compareTo(other.getName());
  }
}
