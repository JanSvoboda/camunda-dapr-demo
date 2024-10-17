package com.example.registercustomer.dapr;

import java.io.IOException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dapr.serializer.DaprObjectSerializer;
import io.dapr.utils.TypeRef;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

@RequiredArgsConstructor
public class JacksonObjectSerializer implements DaprObjectSerializer {

    private final ObjectMapper objectMapper;

    @Override
    public byte[] serialize(final Object state) throws IOException {
        if (state == null) {
            return new byte[0];
        }

        return objectMapper.writeValueAsBytes(state);
    }

    @Override
    public <T> T deserialize(final byte[] data, final TypeRef<T> type) throws IOException {
        return deserialize(data, objectMapper.constructType(type.getType()));
    }

    private <T> T deserialize(final byte[] data, final JavaType javaType) throws IOException {
        if ((javaType == null) || javaType.isTypeOrSubTypeOf(Void.class)) {
            return null;
        }

        if (data == null) {
            return null;
        }

        if (data.length == 0) {
            return null;
        }

        return objectMapper.readValue(data, javaType);
    }

    @Override
    public String getContentType() {
        return MediaType.APPLICATION_JSON_VALUE;
    }
}
