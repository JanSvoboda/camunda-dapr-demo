package com.example.registercustomer.mapper;

import com.example.registercustomer.domain.model.Customer;
import com.example.registercustomer.dto.CustomerDto;
import com.example.registercustomer.dto.CustomerRootDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = AddressMapper.class)
public interface CustomerMapper {

    Customer fromCustomerDto(CustomerDto customerDto);

    @Mappings({
            @Mapping(target = "customer", source = ".")})
    CustomerRootDto toCustomerRootDto(Customer customer);

    CustomerDto toCustomerDto(Customer customer);

}
