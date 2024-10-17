package com.example.registercustomer.configuration;

import com.example.registercustomer.dapr.JacksonObjectSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaprConfig {

    @Bean
    DaprClient getDaprClient(final ObjectMapper objectMapper) {
        var stateSerializer = new JacksonObjectSerializer(objectMapper);
        return new DaprClientBuilder().withStateSerializer(stateSerializer).build();
    }
}
