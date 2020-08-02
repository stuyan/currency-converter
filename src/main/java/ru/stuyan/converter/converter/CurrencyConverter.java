package ru.stuyan.converter.converter;

import ru.stuyan.converter.entity.Currency;
import ru.stuyan.converter.entity.Exchange;
import ru.stuyan.converter.response.ValCurs;
import ru.stuyan.converter.response.Valute;

import java.util.List;

public interface CurrencyConverter {

    List<Exchange> valCursToExchanges(ValCurs valCurs);

    Currency valuteToCurrency(Valute valute);

}
