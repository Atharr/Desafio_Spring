package br.com.dh.Desafio_Spring.repository;

import br.com.dh.Desafio_Spring.dto.CustomerDTO;
import br.com.dh.Desafio_Spring.model.Customer;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
/**
 * @class CustomerRepo
 * @classdesc Customer repository - reads & writes customer data from/to a JSON file.
 */
public class CustomerRepo {
  public final String linkFile = "src/main/resources/customers.json";

  ObjectMapper mapper = new ObjectMapper();

  /**
   * @name getAll
   * @description Reads all customers from a JSON file and produces a list.
   * @return The customer list.
   */
  public List<Customer> getAll() {
    List<Customer> customers = null;
    try {
      customers = Arrays.asList(mapper.readValue(new File(linkFile), Customer[].class));
    } catch (Exception ex) {
      System.out.println("Erro ao ler o arquivo.");
    }
    return customers;
  }

  /**
   * @name getCustomer
   * @description Reads all customers from a JSON file and finds the specified customer by id.
   * @param id - the id of the customer to be fetched.
   * @return The product item or an empty Optional.
   */
  public Optional<Customer> getCustomer(Long id) {
    List<Customer> customers = getAll();

    for (Customer c: customers) {
      if (c.getCustomerId().equals(id)) {
        return Optional.of(c);
      }
    }
    return Optional.empty();
  }

  /**
   * @name save
   * @description Saves a new customer in the customers file.
   * @param newCustomer - the customer to be saved.
   */
  public void save(CustomerDTO newCustomer) {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    List<CustomerDTO> customers = new ArrayList<>(getAll()).stream().map(CustomerDTO::new).collect(Collectors.toList());
    customers.add(newCustomer);

    try {
      writer.writeValue(new File(linkFile), customers);
    } catch (Exception ex) {
      System.out.println("Erro ao gravar o arquivo.");
    }
  }
}
