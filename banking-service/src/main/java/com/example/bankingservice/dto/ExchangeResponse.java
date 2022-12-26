package com.example.bankingservice.dto;



import java.util.List;

public class ExchangeResponse {

    private List<CurrencyUsdDto> values;

    public List<CurrencyUsdDto> getValues() {
        return values;
    }

    public void setValues(List<CurrencyUsdDto> values) {
        this.values = values;
    }
}
