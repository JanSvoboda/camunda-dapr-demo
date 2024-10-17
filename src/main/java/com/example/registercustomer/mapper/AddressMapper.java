package com.example.registercustomer.mapper;

import com.example.registercustomer.domain.model.Address;
import com.example.registercustomer.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//@Mapper(componentModel = "spring")
@Mapper
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    Address fromAddressDto(AddressDto addressDto);
    AddressDto toAddressDto(Address address);
}
