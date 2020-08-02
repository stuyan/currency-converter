package ru.stuyan.converter.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HistoryDto extends ConverterDto {

    private Date date;

}
