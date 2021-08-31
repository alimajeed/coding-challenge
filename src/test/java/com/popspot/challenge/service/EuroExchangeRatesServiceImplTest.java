package com.popspot.challenge.service;

import com.popspot.challenge.cache.ExchangeRatesCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EuroExchangeRatesServiceImplTest {

    @Mock
    ExchangeRatesCache exchangeRatesCache;

    @InjectMocks
    EuroExchangeRatesServiceImpl exchangeRatesService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getExchangeRate() {
        Double base = 1.34;
        Double target = 13.3;
        when(exchangeRatesCache.getRate("USD")).thenReturn(base);
        when(exchangeRatesCache.getRate("EUR")).thenReturn(target);

        //when
        Double exchangeRate = exchangeRatesService.getExchangeRate("USD", "EUR");

        //then
        assertEquals(target / base, exchangeRate);
    }

    @Test
    void getSupportedCurrencies() {
        Map<String, Double> ratesMap = new HashMap<>();
        ratesMap.put("USD", 1.23);
        ratesMap.put("EUR", 12.23);
        when(exchangeRatesCache.getCurrencyRatesMap()).thenReturn(ratesMap);

        //when
        List<String> supportedCurrencies = exchangeRatesService.getSupportedCurrencies();

        //then
        assertNotNull(supportedCurrencies);
        assertEquals(2, supportedCurrencies.size());
    }
}