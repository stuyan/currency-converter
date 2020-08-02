package ru.stuyan.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stuyan.converter.dto.ConverterDto;
import ru.stuyan.converter.dto.CurrencyDto;
import ru.stuyan.converter.repository.CurrencyRepository;
import ru.stuyan.converter.repository.ExchangeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private DataBankService bankService;

    @Autowired
    private HistoryService historyService;

    @Override
    public List<CurrencyDto> getCurrencyList() {
        var listDto = new ArrayList<CurrencyDto>();
        var currencyList = currencyRepository.findAll();
        for (var c : currencyList) {
            var dto = new CurrencyDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public ConverterDto convert(ConverterDto dto) {
        var fromRatio = exchangeRepository.findRatioByCurrency(dto.getFromCurrency());
        var toRatio = exchangeRepository.findRatioByCurrency(dto.getToCurrency());
        if (fromRatio == null || toRatio == null) {
            var exchanges = bankService.getData();
            for (var e : exchanges) {
                var currencyId = e.getCurrency().getId();
                if (currencyId != null && currencyId.equals(dto.getFromCurrency())) {
                    fromRatio = e.getRatio();
                }
                if (currencyId != null && currencyId.equals(dto.getToCurrency())) {
                    toRatio = e.getRatio();
                }
            }
        }

        if (fromRatio == null || toRatio == null) {
            dto.setError("Невозможно произвести конвертацию.");
        } else {
            var result = (fromRatio * dto.getFromValue()) / toRatio;
            dto.setToValue(result);

            historyService.saveHistory(dto);
        }
        return dto;
    }

}
