package ru.stuyan.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stuyan.converter.dto.ConverterDto;
import ru.stuyan.converter.dto.HistoryDto;
import ru.stuyan.converter.dto.HistoryFilterDto;
import ru.stuyan.converter.entity.History;
import ru.stuyan.converter.repository.CurrencyRepository;
import ru.stuyan.converter.repository.HistoryRepository;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void saveHistory(ConverterDto dto) {
        var entity = new History();
        var currencyFromRef = currencyRepository.getOne(dto.getFromCurrency());
        entity.setFromCurrency(currencyFromRef);
        var currencyToRef = currencyRepository.getOne(dto.getToCurrency());
        entity.setToCurrency(currencyToRef);
        entity.setFromSum(dto.getFromValue());
        entity.setToSum(dto.getToValue());
        entity.setDate(new Date());
        var user = userService.getCurrentUser();
        entity.setUser(user);
        historyRepository.save(entity);
    }

    @Override
    public List<HistoryDto> getHistory(HistoryFilterDto dto) {
        List<History> fromDB;
        var user = userService.getCurrentUser();
        if (dto.getDate() == null) {
            fromDB = historyRepository.findByUser(user);
        } else {
            var date = dto.getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate()
                    .atStartOfDay();
            fromDB = historyRepository.findByCurrencyAndDate(dto.getFromCurrency(),
                    dto.getToCurrency(), date, user.getId());
        }
        return convert(fromDB);
    }

    private List<HistoryDto> convert(List<History> histories) {
        var historiesDto = new ArrayList<HistoryDto>();
        for (History h : histories) {
            var dto = new HistoryDto();
            dto.setDate(h.getDate());
            dto.setToValue(h.getToSum());
            dto.setFromValue(h.getFromSum());
            dto.setToCurrency(h.getToCurrency().getId());
            dto.setFromCurrency(h.getFromCurrency().getId());
            dto.setToCurrencyName(h.getToCurrency().getName());
            dto.setFromCurrencyName(h.getFromCurrency().getName());
            historiesDto.add(dto);
        }
        return historiesDto;
    }

}
