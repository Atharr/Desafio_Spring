package br.com.dh.Desafio_Spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDTO {
    private Long productId;
    private Integer quantity;
}
