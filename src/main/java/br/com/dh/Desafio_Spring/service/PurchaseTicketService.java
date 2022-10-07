package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.ProductRequestDTO;
import br.com.dh.Desafio_Spring.dto.PurchaseTicketDTO;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.exception.ServiceUnavailableException;
import br.com.dh.Desafio_Spring.exception.StockNotAvailableException;
import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.model.PurchaseTicket;
import br.com.dh.Desafio_Spring.repository.ProductRepo;
import br.com.dh.Desafio_Spring.repository.PurchaseTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PurchaseTicketService implements IPurchaseTicket {

    @Autowired
    private PurchaseTicketRepo repoTicket;
    @Autowired
    private ProductRepo repoProduct;

    @Override
    public PurchaseTicketDTO save(List<ProductRequestDTO> newPurchaseTicket) throws ServiceUnavailableException {
        List<Product> product = repoProduct.getAll();

        List<Product> listProductFilter = new ArrayList<>();
        product.forEach(p -> {

            newPurchaseTicket.forEach(n -> {
                if(repoProduct.getById(n.getProductId()).isEmpty()){
                    throw new NotFoundException("Produto não existe");
                }
                    if (Objects.equals(p.getProductId(), n.getProductId())) {
                        if (p.getQuantity() - n.getQuantity() < 0){
                            throw new StockNotAvailableException("Stock de produto insuficiente");
                    }
                    repoProduct.decreaseProductStock(n.getProductId(),n.getQuantity());
                    p.setQuantity(n.getQuantity());
                    listProductFilter.add(p);
                }
            });
        });
        BigDecimal totalPrice = listProductFilter.stream()
                .map((p) -> BigDecimal.valueOf(p.getQuantity()).multiply(p.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        PurchaseTicket newTicket = repoTicket.save(listProductFilter, totalPrice);
        return new PurchaseTicketDTO(newTicket);
    }
}
