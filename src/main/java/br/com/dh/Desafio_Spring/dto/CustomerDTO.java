package br.com.dh.Desafio_Spring.dto;

import br.com.dh.Desafio_Spring.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private Long customerId;
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name is required")
    private String lastName;
    private String phone;
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email(message = "Email invalido")
    private String email;

    private String address;
    private String city;
    private String state;
    private String zipcode;

    public CustomerDTO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.city = customer.getCity();
        this.state = customer.getState();
        this.zipcode = customer.getZipcode();
    }
}
