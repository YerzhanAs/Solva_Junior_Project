package com.example.bankingservice.services;

import com.example.bankingservice.models.CurrencyUsd;
import com.example.bankingservice.models.Transaction;
import com.example.bankingservice.repositories.CurrencyUsdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CurrencyUsdService {

    private final CurrencyUsdRepository currencyUsdRepositiory;

    @Autowired
    public CurrencyUsdService(CurrencyUsdRepository currencyUsdRepositiory) {
        this.currencyUsdRepositiory = currencyUsdRepositiory;
    }

    @Transactional
    public void save(CurrencyUsd currencyUsd){
        enrichCurrencyUsd(currencyUsd);
        currencyUsdRepositiory.save(currencyUsd);
    }

    public List<CurrencyUsd> findAll(){
        return currencyUsdRepositiory.findAll();
    }



    private void enrichCurrencyUsd(CurrencyUsd currencyUsd){
        currencyUsd.setCreatedAt(LocalDateTime.now());
    }


}
