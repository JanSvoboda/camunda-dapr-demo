package com.example.registercustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@JsonInclude(Include.NON_NULL)
//@Jacksonized
//@Builder
public class CustomerDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    AddressDto address;
}
