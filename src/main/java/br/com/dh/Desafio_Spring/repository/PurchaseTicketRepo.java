package br.com.dh.Desafio_Spring.repository;

import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.model.PurchaseTicket;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PurchaseTicketRepo {
    private final String linkFile = "src/main/resources/purchase-result.json";
    ObjectMapper mapper = new ObjectMapper();

    public List<PurchaseTicket> getAll() {
        List<PurchaseTicket> purchases = null;
        try {
            purchases = Arrays.asList(mapper.readValue(new File(linkFile), PurchaseTicket[].class));
        } catch (Exception ex) {
            System.out.println("Erro ao ler o arquivo.");
        }
        return purchases;
    }

    public PurchaseTicket save(List<Product> newTicket, BigDecimal totalPrice) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<PurchaseTicket> ticketList = new ArrayList<>(getAll());
        PurchaseTicket ticket = new PurchaseTicket((long) (ticketList.size()+1), newTicket, totalPrice);
        ticketList.add(ticket);

        try {
            writer.writeValue(new File(linkFile), ticketList);
        } catch (Exception ex) {
            System.out.println("Erro ao gravar o arquivo.");
        }
        return ticket;
    }



}
