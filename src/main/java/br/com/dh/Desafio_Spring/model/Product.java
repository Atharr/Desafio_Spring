package br.com.dh.Desafio_Spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Override
  public String toString() {
    return "Product{" +
            "productId=" + productId +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", brand='" + brand + '\'' +
            ", price=" + price +
            ", quantity=" + quantity +
            ", freeShipping=" + freeShipping +
            ", prestige='" + prestige + '\'' +
            '}';
  }

  @Override
/**
 * @name compareTo
 * @description comparison needed for sort methods
 * @param Product other - the
 * @return -1, 0, +1 if the compared item is lesser, equal or greater than the base item
 */
  public int compareTo(Product other) {
    return this.getName().compareTo(other.getName());
  }
}
