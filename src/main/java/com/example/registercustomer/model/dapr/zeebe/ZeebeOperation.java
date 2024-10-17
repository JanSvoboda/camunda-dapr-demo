package com.example.registercustomer.model.dapr.zeebe;


import com.fasterxml.jackson.annotation.JsonValue;

public enum ZeebeOperation {

    CREATE_INSTANCE("create-instance"),
    THROW_ERROR("throw-error");

    private final String operationName;

    ZeebeOperation(String operationName) {
        this.operationName = operationName;
    }

    @JsonValue
    public String getOperationName() {
        return operationName;
    }
}
