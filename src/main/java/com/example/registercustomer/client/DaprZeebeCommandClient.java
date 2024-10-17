package com.example.registercustomer.client;

import com.example.registercustomer.model.dapr.zeebe.ZeebeCommand;
import com.example.registercustomer.model.dapr.zeebe.ZeebeCreateInstanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "daprZeebeCommandClient", url = "http://localhost:3500", path = "/v1.0/bindings/dapr-zeebe-command")
@FeignClient(name = "daprZeebeCommandClient", url = "https://camunda-zeebe.kubernetes.localhost", path = "/v1.0/bindings/dapr-zeebe-command")
public interface DaprZeebeCommandClient {
    @PostMapping
    ResponseEntity<ZeebeCreateInstanceResponse> sendCommand(
            @RequestBody ZeebeCommand zeebeCommand
    );
}
