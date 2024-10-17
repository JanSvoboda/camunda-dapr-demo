package com.example.registercustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Valid
@JsonInclude(Include.NON_NULL)
public class CustomerRootDto {

    private CustomerDto customer;

}
