package com.popspot.challenge.bootstrap;

import com.popspot.challenge.service.DataRetrieveService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExchangeRatesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private DataRetrieveService service;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        service.loadData();
    }

}
