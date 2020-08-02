package ru.stuyan.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.stuyan.converter.dto.ConverterDto;
import ru.stuyan.converter.service.ConverterService;

@Controller
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @GetMapping("/")
    public String beforeConversion(Model model) {
        var currencyList = converterService.getCurrencyList();
        model.addAttribute("currencies", currencyList);
        model.addAttribute("form", new ConverterDto());
        return "main";
    }

    @PostMapping("/conversion")
    public String forConversion(Model model, ConverterDto form) {
        form = converterService.convert(form);
        var currencyList = converterService.getCurrencyList();
        model.addAttribute("currencies", currencyList);
        model.addAttribute("form", form);
        return "main";
    }

}
