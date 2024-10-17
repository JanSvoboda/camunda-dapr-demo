package com.example.registercustomer.controller;

import com.example.registercustomer.client.DaprZeebeCommandClient;
import com.example.registercustomer.domain.model.Customer;
import com.example.registercustomer.dto.CustomerDto;
import com.example.registercustomer.dto.CustomerRootDto;
import com.example.registercustomer.dto.FindCustomer;
import com.example.registercustomer.mapper.CustomerMapper;
import com.example.registercustomer.model.dapr.zeebe.ZeebeCommand;
import com.example.registercustomer.model.dapr.zeebe.ZeebeData;
import com.example.registercustomer.model.dapr.zeebe.ZeebeOperation;
import com.example.registercustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class CustomerController {

    private static final String CUSTOMER_NOT_FOUND = "customer-not-found";
    private static final String MISSING_ID = "missing-id";
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final DaprZeebeCommandClient daprZeebeCommandClient;

    @PostMapping(path = "/find-customer")
    public ResponseEntity<CustomerRootDto> findCustomerById(@RequestHeader HttpHeaders headers, @RequestBody(required = false) FindCustomer findCustomer) {

        if (findCustomer.id() == null) {
            return throwError(Long.parseLong(Objects.requireNonNull(headers.get("X-Zeebe-Job-Key")).getFirst()), MISSING_ID, "Missing ID");
        }

        Customer customer = customerService.findCustomerById(findCustomer.id());
        if (customer == null) {
            return throwError(Long.parseLong(Objects.requireNonNull(headers.get("X-Zeebe-Job-Key")).getFirst()), CUSTOMER_NOT_FOUND, "Customer not found");
        }

        return ResponseEntity.ok(customerMapper.toCustomerRootDto(customer));
    }

    @PostMapping(path = "/register-customer")
    public ResponseEntity<CustomerRootDto> registerCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.saveCustomer(customerMapper.fromCustomerDto(customerDto));
        return new ResponseEntity<>(customerMapper.toCustomerRootDto(customer), HttpStatus.CREATED);
    }

    private ResponseEntity<CustomerRootDto> throwError(long jobKey, String errorCode, String errorMessage) {
        ZeebeCommand command = ZeebeCommand.builder()
                .operation(ZeebeOperation.THROW_ERROR)
                .data(ZeebeData.builder()
                        .jobKey(jobKey)
                        .errorCode(errorCode)
                        .errorMessage(errorMessage)
                        .build())
                .build();
        var response = daprZeebeCommandClient.sendCommand(command);
        log.info("Response: {}", response.getBody());
        return ResponseEntity.ok(new CustomerRootDto());
    }
}
