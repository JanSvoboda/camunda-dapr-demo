package com.example.registercustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CamundaRegisterCustomerExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamundaRegisterCustomerExampleApplication.class, args);
    }

}
