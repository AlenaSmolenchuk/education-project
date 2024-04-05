package ru.mts.educationproject.educationprojectstarter.utilstarter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Serializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String encodedInfo = Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
        gen.writeString(encodedInfo);
    }
}
