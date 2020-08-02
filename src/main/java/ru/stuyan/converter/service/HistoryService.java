package ru.stuyan.converter.service;

import ru.stuyan.converter.dto.ConverterDto;
import ru.stuyan.converter.dto.HistoryDto;
import ru.stuyan.converter.dto.HistoryFilterDto;

import java.util.List;

public interface HistoryService {

    void saveHistory(ConverterDto dto);

    List<HistoryDto> getHistory(HistoryFilterDto dto);

}
