package ru.stuyan.converter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.stuyan.converter.converter.CurrencyConverter;
import ru.stuyan.converter.entity.Exchange;
import ru.stuyan.converter.repository.ExchangeRepository;
import ru.stuyan.converter.response.ValCurs;
import ru.stuyan.converter.response.Valute;

import java.util.List;

@Service
public class DataBankServiceImpl implements DataBankService {

    @Value("${cbr.currency.url}")
    private String url;

    @Autowired
    private CurrencyConverter converter;

    @Autowired
    private ExchangeRepository repository;

    @Override
    @Transactional
    public List<Exchange> getData() {
        // запрос по урлу, получение данных
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        var entity = new HttpEntity<>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, ValCurs.class);
        var result = response.getBody();
        result.getValutes().add(createRub());

        // конвертация
        var exchanges = converter.valCursToExchanges(result);

        // сохранение в бд
        return repository.saveAll(exchanges);
    }

    private Valute createRub() {
        var rubVal = new Valute();
        rubVal.setCharCode("RUB");
        rubVal.setName("Российский рубль");
        rubVal.setNominal(1);
        rubVal.setValue(1f);
        return rubVal;
    }

}
