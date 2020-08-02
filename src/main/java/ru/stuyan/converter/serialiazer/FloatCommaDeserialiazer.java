package ru.stuyan.converter.serialiazer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FloatCommaDeserialiazer extends JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        try {
            var value = jsonParser.getText();
            value = value.replace(",", ".");
            return Float.valueOf(value);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
