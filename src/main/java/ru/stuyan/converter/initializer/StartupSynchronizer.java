package ru.stuyan.converter.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.stuyan.converter.service.DataBankService;

@Component
@Slf4j
public class StartupSynchronizer implements ApplicationRunner {

    @Autowired
    private DataBankService dataBankService;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Запущена синхронизация на старте");
        dataBankService.getData();
    }

}
