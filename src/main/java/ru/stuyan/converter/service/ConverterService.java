package ru.stuyan.converter.service;

import ru.stuyan.converter.dto.ConverterDto;
import ru.stuyan.converter.dto.CurrencyDto;

import java.util.List;

public interface ConverterService {

    List<CurrencyDto> getCurrencyList();

    ConverterDto convert(ConverterDto dto);

}
