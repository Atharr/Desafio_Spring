package br.com.dh.Desafio_Spring.repository;

import br.com.dh.Desafio_Spring.model.Product;
import br.com.dh.Desafio_Spring.model.PurchaseTicket;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<PurchaseTicket> save(List<Product> newTicket) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        PurchaseTicket ticket = new PurchaseTicket(getAll().size()+1,newTicket,);

        List<PurchaseTicket> products = new ArrayList<>(getAll());
        products.addAll(newTicket);

        try {
            writer.writeValue(new File(linkFile), products);
        } catch (Exception ex) {
            System.out.println("Erro ao gravar o arquivo.");
        }
        return products;
    }



}
