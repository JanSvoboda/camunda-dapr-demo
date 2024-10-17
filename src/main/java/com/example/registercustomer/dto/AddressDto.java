package com.example.registercustomer.dto;

import lombok.Data;

@Data
public class AddressDto {
    String street;
    String city;
    String country;
    String postCode;
}
