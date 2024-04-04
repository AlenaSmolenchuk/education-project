package ru.mts.educationproject.educationprojectstarter.utilstarter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Deserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        String s = p.getValueAsString();
        return new String(Base64.getDecoder().decode(s), StandardCharsets.UTF_8);
    }
}
