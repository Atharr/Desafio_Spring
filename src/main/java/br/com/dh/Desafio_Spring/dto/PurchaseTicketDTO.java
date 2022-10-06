package br.com.dh.Desafio_Spring.dto;

import br.com.dh.Desafio_Spring.model.PurchaseTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTicketDTO {
    private PurchaseTicket ticket;
}
