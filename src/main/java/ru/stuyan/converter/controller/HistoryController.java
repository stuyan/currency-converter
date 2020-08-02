package ru.stuyan.converter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.stuyan.converter.dto.HistoryFilterDto;
import ru.stuyan.converter.service.ConverterService;
import ru.stuyan.converter.service.HistoryService;

@Controller
public class HistoryController {

    @Autowired
    private HistoryService service;

    @Autowired
    private ConverterService converterService;

    @GetMapping("/history")
    public String historyPage(Model model, HistoryFilterDto filter) {
        var currencyList = converterService.getCurrencyList();
        model.addAttribute("currencies", currencyList);
        var result = service.getHistory(filter);
        model.addAttribute("history", result);
        model.addAttribute("filter", filter);
        return "history";
    }

}
