package com.popspot.challenge.service;

import com.popspot.challenge.cache.ExchangeRatesCache;
import com.popspot.challenge.exception.CurrencyNotSupportedException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Profile({"euro", "default"})
public class EuroExchangeRatesServiceImpl implements ExchangeRatesService{

    private ExchangeRatesCache exchangeRatesCache;

    @Override
    public Double getExchangeRate(String baseCurr, String targetCurr) {
        Double baseCurrRate = exchangeRatesCache.getRate(baseCurr);
        Double targetCurrRate = exchangeRatesCache.getRate(targetCurr);
        if (null == baseCurrRate || null == targetCurrRate){
            throw new CurrencyNotSupportedException("currency exchange not supported for currencies, base : " + baseCurr + " and target : " + targetCurr);
        }
        return targetCurrRate/baseCurrRate;
    }

    @Override
    public List<String> getSupportedCurrencies() {
        return new ArrayList<>(exchangeRatesCache.getCurrencyRatesMap().keySet());
    }
}
