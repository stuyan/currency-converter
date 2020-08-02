package ru.stuyan.converter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.stuyan.converter.entity.Currency;
import ru.stuyan.converter.entity.Exchange;
import ru.stuyan.converter.repository.CurrencyRepository;
import ru.stuyan.converter.response.ValCurs;
import ru.stuyan.converter.response.Valute;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyConverterImpl implements CurrencyConverter {

    @Autowired
    private CurrencyRepository repository;

    @Override
    public List<Exchange> valCursToExchanges(ValCurs valCurs) {
        var exchanges = new ArrayList<Exchange>();
        for (var v : valCurs.getValutes()) {
            var exchange = new Exchange();
            exchange.setDate(valCurs.getDate());
            exchange.setCurrency(getCurrencyFromDbOrCreate(v));
            var ratio = v.getValue() / v.getNominal();
            exchange.setRatio(ratio);
            exchanges.add(exchange);
        }
        return exchanges;
    }

    @Override
    public Currency valuteToCurrency(Valute valute) {
        var currency = new Currency();
        currency.setId(valute.getCharCode());
        currency.setName(valute.getName());
        return currency;
    }

    private Currency getCurrencyFromDbOrCreate(Valute valute) {
        return repository.findById(valute.getCharCode())
                .orElse(valuteToCurrency(valute));
    }

}
