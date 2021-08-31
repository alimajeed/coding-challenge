package com.popspot.challenge.controller;

import com.popspot.challenge.exception.ApiExceptionHandler;
import com.popspot.challenge.exception.CurrencyNotSupportedException;
import com.popspot.challenge.service.ExchangeRatesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExchangeRatesControllerTest {

    @Mock
    ExchangeRatesService exchangeRatesService;

    @InjectMocks
    ExchangeRatesController exchangeRatesController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(exchangeRatesController)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void getExchangeRate() throws Exception {
        when(exchangeRatesService.getExchangeRate(anyString(), anyString())).thenReturn(1.1);
        mockMvc.perform(get(ExchangeRatesController.BASE_URL + "?base=abc&target=xyz")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload", equalTo(1.1)));
    }

    @Test
    void testGetExchangeRateBadRequest() throws Exception {
        mockMvc.perform(get(ExchangeRatesController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCurrencyRateNotSupported() throws Exception {
        when(exchangeRatesService.getExchangeRate(anyString(), anyString())).thenThrow(CurrencyNotSupportedException.class);
        mockMvc.perform(get(ExchangeRatesController.BASE_URL + "?base=abc&target=xyz")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void getSupportedCurrencies() throws Exception {
        when(exchangeRatesService.getSupportedCurrencies()).thenReturn(List.of("USD", "EUR"));
        mockMvc.perform(get(ExchangeRatesController.BASE_URL + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.payload", hasSize(2)));
    }
}