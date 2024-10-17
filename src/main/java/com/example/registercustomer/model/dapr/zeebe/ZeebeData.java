package com.example.registercustomer.model.dapr.zeebe;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
@Builder
@Data
public class ZeebeData {
    @JsonInclude(Include.NON_DEFAULT)
    long processDefinitionKey;
    @JsonInclude(Include.NON_DEFAULT)
    long jobKey;
    String version;
    String bpmnProcessId;
    Object variables;
    boolean withResult;
    String requestTimeout;
    List<String> fetchVariables;
    String errorCode;
    String errorMessage;
}
