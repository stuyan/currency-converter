package ru.stuyan.converter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConverterDto {

    private String fromCurrency;

    private String toCurrency;

    private String fromCurrencyName;

    private String toCurrencyName;

    private int fromValue;

    private float toValue;

    private String error;

}
