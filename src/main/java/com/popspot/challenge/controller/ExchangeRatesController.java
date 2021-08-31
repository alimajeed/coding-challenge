package com.popspot.challenge.controller;

import com.popspot.challenge.payload.ResponsePayload;
import com.popspot.challenge.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ExchangeRatesController.BASE_URL)
@AllArgsConstructor
public class ExchangeRatesController {

    public static final String BASE_URL = "/api/v1";

    private ExchangeRatesService exchangeRatesService;

    @GetMapping
    public ResponseEntity<ResponsePayload> getExchangeRate(
            @RequestParam(name = "base") String base,
            @RequestParam(name = "target") String target){
        ResponsePayload responsePayload = ResponsePayload.builder()
                .payload(exchangeRatesService.getExchangeRate(base, target))
                .message("success")
                .build();
        return ResponseEntity.ok().body(responsePayload);
    }

    @GetMapping("/list")
    public ResponseEntity<ResponsePayload> getSupportedCurrencies(){
        ResponsePayload responsePayload = ResponsePayload.builder()
                .payload(exchangeRatesService.getSupportedCurrencies())
                .message("success")
                .build();
        return ResponseEntity.ok().body(responsePayload);
    }
}
