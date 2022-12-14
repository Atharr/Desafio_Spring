package br.com.dh.Desafio_Spring.service;

import br.com.dh.Desafio_Spring.dto.CustomerDTO;
import br.com.dh.Desafio_Spring.exception.NotFoundException;
import br.com.dh.Desafio_Spring.model.Customer;

import java.util.List;
import java.util.Map;

public interface ICustomer {
  Customer getCustomer(Long id) throws NotFoundException;
  List<Customer> getAll(Map<String,String> params);
  Customer save(CustomerDTO customer);
  Customer updateOne(Long id, CustomerDTO customerUpdated);
  void deleteOne(Long id);
}
