package com.example.registercustomer.model.dapr.zeebe;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ZeebeCommand {
    ZeebeData data;
    ZeebeOperation operation;
}
