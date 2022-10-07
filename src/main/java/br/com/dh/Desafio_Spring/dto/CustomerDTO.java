package br.com.dh.Desafio_Spring.dto;

import br.com.dh.Desafio_Spring.model.Customer;
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
    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name must not be empty")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;
    private String phone;
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email(message = "Email inválido")
    private String email;
    @NotNull(message = "Address is required")
    @NotEmpty(message = "Address must not be empty")
    private String address;
    @NotNull(message = "City is required")
    @NotEmpty(message = "City must not be empty")
    private String city;
    @NotNull(message = "State is required")
    @NotEmpty(message = "State must not be empty")
    private String state;
    @NotNull(message = "Zipcode is required")
    @NotEmpty(message = "Zipcode must not be empty")
    private String zipcode;

    public CustomerDTO(Customer customer) {
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
