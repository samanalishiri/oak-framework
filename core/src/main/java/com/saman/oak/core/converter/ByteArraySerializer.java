package com.saman.oak.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Base64;

public class ByteArraySerializer extends JsonSerializer<byte[]> {

    @Override
    public void serialize(byte[] image, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(Base64.getEncoder().encodeToString(image));
    }
}