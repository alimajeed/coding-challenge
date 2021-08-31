package com.popspot.challenge.service;

import com.popspot.challenge.cache.ExchangeRatesCache;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@AllArgsConstructor
@Profile({"csv", "default"})
public class CsvDataRetrieveService implements DataRetrieveService{

    private ExchangeRatesCache cache;

    public void loadData() throws IOException {
        String splitBy = ",";
        String fileName = "eurofxref.csv";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String[] currencyArray = br.readLine().split(splitBy);
        String[] ratesArray = br.readLine().split(splitBy);
        if (currencyArray.length != ratesArray.length){
            throw new IllegalArgumentException("currency rates mapping is invalid");
        }
        for (int i = 1 ;  i < currencyArray.length - 1; i++){
            String key = currencyArray[i].trim();
            Double value = Double.valueOf(ratesArray[i].trim());
            if (null == key || null == value){
                continue;
            }
            cache.getCurrencyRatesMap().put(key, value);
        }
    }
}
