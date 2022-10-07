package br.com.dh.Desafio_Spring.model;

import br.com.dh.Desafio_Spring.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Comparable<Customer> {
  private Long customerId;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private String address;
  private String city;
  private String state;
  private String zipcode;



  public Customer(Long i, CustomerDTO newCustomer) {
    this.customerId = i;
    this.firstName = newCustomer.getFirstName();
    this.lastName = newCustomer.getLastName();
    this.phone = newCustomer.getPhone();
    this.email = newCustomer.getEmail();
    this.address = newCustomer.getAddress();
    this.city = newCustomer.getCity();
    this.state = newCustomer.getState();
    this.zipcode = newCustomer.getZipcode();
  }

  @Override
/**
 * @name compareTo
 * @description comparison needed for sort methods
 * @param Customer other - the item to compare
 * @return -1, 0, +1 if the compared item is lesser, equal or greater than the base item
 */
  public int compareTo(Customer other) {
    return this.getCustomerId().compareTo(other.getCustomerId());
  }
}
