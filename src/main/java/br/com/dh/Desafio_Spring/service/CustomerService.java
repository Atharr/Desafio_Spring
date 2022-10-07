package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.CustomerDTO;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Customer;
import br.com.dh.Desafio_Spring.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService implements ICustomer {
  @Autowired
  private CustomerRepo repo;

  private List<Customer> sortList(List<Customer> customers, String order) {
    Comparator<Customer> byFirstName = Comparator.comparing(Customer::getFirstName);
    Comparator<Customer> byLastName = Comparator.comparing(Customer::getLastName);

    switch (order) {
      case "0":
        return customers.stream().sorted(byFirstName).collect(Collectors.toList());
      case "1":
        return customers.stream().sorted(byFirstName.reversed()).collect(Collectors.toList());
      case "2":
        return customers.stream().sorted(byLastName).collect(Collectors.toList());
      case "3":
        return customers.stream().sorted(byLastName.reversed()).collect(Collectors.toList());
    }

    return customers.stream().sorted().collect(Collectors.toList());
  }

  private List<Customer> filterList(List<Customer> customers, Map<String,String> filters) {
    Stream<Customer> s = customers.stream();

    for (Map.Entry<String,String> f: filters.entrySet()) {
      switch (f.getKey()) {
        case "city":
          s = s.filter(customer -> customer.getCity().equalsIgnoreCase(f.getValue()));
          break;
        case "state":
          s = s.filter(customer -> customer.getState().equalsIgnoreCase(f.getValue()));
          break;
        case "zipcode":
          s = s.filter(customer -> customer.getZipcode().equalsIgnoreCase(f.getValue()));
          break;
      }
    }

    return s.collect(Collectors.toList());
  }

  @Override
  public Customer getCustomer(Long id) throws NotFoundException {
    Optional<Customer> customer = repo.getCustomer(id);

    if (customer.isEmpty()) {
      throw new NotFoundException("Cliente não encontrado.");
    }
    return customer.get();
  }

  @Override
  public List<Customer> getAll(Map<String, String> params) {
    List<Customer> customers = repo.getAll();

    if (params.containsKey("order")) {
      customers = sortList(customers, params.get("order"));
      params.remove("order");
    }

    if (!params.isEmpty()) {
      customers = filterList(customers, params);
    }

    return customers;
  }

  @Override
  public Customer save(CustomerDTO customer) {
    Long customerListSize = (long) repo.getAll().size();
    Customer newCustomer = new Customer(customerListSize + 1, customer);
    repo.save(newCustomer);
    return newCustomer;
  }

  @Override
  public Customer updateOne(Long id, CustomerDTO customerUpdated) throws NotFoundException {
    Optional<Customer> optionalCustomer = repo.getCustomer(id);
    if (optionalCustomer.isEmpty()){
      throw new NotFoundException("Cliente não encontrado");
    }
    return repo.updateOne(id, customerUpdated);
  }

  @Override
  public void deleteOne(Long id) {
    Optional<Customer> optionalCustomer = repo.getCustomer(id);
    if (optionalCustomer.isEmpty()){
      throw new NotFoundException("Cliente não encontrado");
    }
    repo.deleteOne(id);
  }


}
