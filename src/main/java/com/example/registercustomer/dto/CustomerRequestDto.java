package com.example.registercustomer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
@Builder
public class CustomerRequestDto extends CustomerDto{
    HeaderDto header;
}
