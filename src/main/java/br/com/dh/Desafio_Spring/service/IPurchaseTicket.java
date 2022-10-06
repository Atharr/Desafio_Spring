package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.ProductRequestDTO;
import br.com.dh.Desafio_Spring.model.PurchaseTicket;

import java.util.List;

public interface IPurchaseTicket {
    PurchaseTicket save(List<ProductRequestDTO> newPurchaseTicket);
}
