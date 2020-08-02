package ru.stuyan.converter.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class HistoryFilterDto {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    private String fromCurrency;

    private String toCurrency;

}
