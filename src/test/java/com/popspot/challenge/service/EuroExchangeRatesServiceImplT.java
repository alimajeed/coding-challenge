package com.popspot.challenge.service;

import com.popspot.challenge.bootstrap.ExchangeRatesBootstrap;
import com.popspot.challenge.cache.ExchangeRatesCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EuroExchangeRatesServiceImplT {

    @Autowired
    ExchangeRatesCache exchangeRatesCache;

    @Autowired
    DataRetrieveService dataRetrieveService;

    ExchangeRatesService exchangeRatesService;

    @BeforeEach
    void setUp() {
        ExchangeRatesBootstrap bootstrap = new ExchangeRatesBootstrap(dataRetrieveService);
        bootstrap.onApplicationEvent(null);

        exchangeRatesService = new EuroExchangeRatesServiceImpl(exchangeRatesCache);
    }

    @Test
    void getExchangeRate() {
        String base = "USD";
        String target = "JPY";

        Double exchangeRate = exchangeRatesService.getExchangeRate(base, target);

        assertEquals(109.810714889302, exchangeRate);
    }

    @Test
    void getSupportedCurrencies() {
        List<String> supportedCurrencies = exchangeRatesService.getSupportedCurrencies();

        assertNotNull(supportedCurrencies);
        assertEquals(32, supportedCurrencies.size());
    }
}