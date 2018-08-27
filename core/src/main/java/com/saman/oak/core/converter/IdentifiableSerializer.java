package com.saman.oak.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.hateoas.Identifiable;

import java.io.IOException;

public class IdentifiableSerializer extends JsonSerializer<Identifiable<?>> {

    @Override
    public void serialize(Identifiable<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        provider.defaultSerializeValue(value.getId(), jgen);
    }
}