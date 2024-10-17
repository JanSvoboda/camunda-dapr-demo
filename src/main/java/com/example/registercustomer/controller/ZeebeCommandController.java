package com.example.registercustomer.controller;

import com.example.registercustomer.client.DaprSdkZeebeCommandClient;
import com.example.registercustomer.client.DaprZeebeCommandClient;
import com.example.registercustomer.model.dapr.zeebe.ZeebeCommand;
import com.example.registercustomer.model.dapr.zeebe.ZeebeCreateInstanceResponse;
import com.example.registercustomer.model.dapr.zeebe.ZeebeData;
import com.example.registercustomer.model.dapr.zeebe.ZeebeOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/dapr")
@RequiredArgsConstructor
@Slf4j
public class ZeebeCommandController {

    private final DaprZeebeCommandClient daprZeebeCommandClient;
    private final DaprSdkZeebeCommandClient daprSdkZeebeCommandClient;

    @PostMapping(path = "/create-instance/{processId}")
    public ResponseEntity<ZeebeCreateInstanceResponse> createInstance(@PathVariable String processId, @RequestBody(required = false) Object variables) {
        ZeebeCommand command = ZeebeCommand.builder()
                .operation(ZeebeOperation.CREATE_INSTANCE)
                .data(ZeebeData.builder()
                        .bpmnProcessId(processId)
                        .variables(variables)
                        .build())
                .build();
        var response = daprZeebeCommandClient.sendCommand(command);
        log.info("Response: {}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping(path = "/create-instance-synchronous/{processId}")
    public ResponseEntity<ZeebeCreateInstanceResponse> createInstanceSynchronous(@PathVariable String processId, @RequestBody(required = false) Object variables) {
        ZeebeCommand command = ZeebeCommand.builder()
                .operation(ZeebeOperation.CREATE_INSTANCE)
                .data(ZeebeData.builder()
                        .bpmnProcessId(processId)
                        .variables(variables)
                        .withResult(true)
                        .build())
                .build();
        var response = daprZeebeCommandClient.sendCommand(command);
        log.info("Response: {}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping(path = "/create-instance-sdk/{processId}")
    public ResponseEntity<String> createInstanceSdk(@PathVariable String processId, @RequestBody(required = false) Object variables) {
        return ResponseEntity.ok(daprSdkZeebeCommandClient.createInstance(processId, variables).block());
    }
}
