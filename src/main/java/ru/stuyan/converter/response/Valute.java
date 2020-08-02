package ru.stuyan.converter.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ru.stuyan.converter.serialiazer.FloatCommaDeserialiazer;

@Getter
@Setter
public class Valute {

    @JsonProperty("ID")
    private String id;

    @JsonProperty("NumCode")
    private String numCode;

    @JsonProperty("CharCode")
    private String charCode;

    @JsonProperty("Nominal")
    private Integer nominal;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Value")
    @JsonDeserialize(using = FloatCommaDeserialiazer.class)
    private Float value;

}
