package com.example.registercustomer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class HeaderDto {
    UUID correlationId;
    UUID providerId;
    UUID partnerId;
}
