package com.example.registercustomer.client;

import com.example.registercustomer.model.dapr.zeebe.ZeebeCommand;
import com.example.registercustomer.model.dapr.zeebe.ZeebeData;
import com.example.registercustomer.model.dapr.zeebe.ZeebeOperation;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.InvokeBindingRequest;
import io.dapr.utils.TypeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DaprSdkZeebeCommandClient {

    @Autowired
    DaprClient client;

    public Mono<String> createInstance(String processId, Object variables) {

        ZeebeCommand command = ZeebeCommand.builder()
                .operation(ZeebeOperation.CREATE_INSTANCE)
                .data(ZeebeData.builder()
                        .bpmnProcessId(processId)
                        .variables(variables)
                        .build())
                .build();

        return client.invokeBinding(
                "dapr-zeebe-command",
                ZeebeOperation.CREATE_INSTANCE.getOperationName(),
                command,
                TypeRef.STRING
                );
    }
}
