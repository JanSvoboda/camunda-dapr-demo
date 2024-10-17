package com.example.registercustomer.model.dapr.zeebe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
public class ZeebeCreateInstanceResponse {
    final long processDefinitionKey;
    final String bpmnProcessId;
    final String version;
    final String processInstanceKey;
    @JsonInclude(Include.NON_NULL)
    final String variables;
}