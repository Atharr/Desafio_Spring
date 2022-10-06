package br.com.dh.Desafio_Spring.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTicket {
    private Long ticketId;
    private List<Product> articles;
    private BigDecimal total;
}
