package com.example.registercustomer.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;

@Data
@Valid
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String firstName;
    @NotEmpty
    String lastName;
    @Email
    @NotEmpty
    String email;
    @Pattern(regexp = "^+\\d{9,15}$", message = "invalid phone number")
    String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    Address address;
}
