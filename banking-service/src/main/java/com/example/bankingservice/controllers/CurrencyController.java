package com.example.bankingservice.controllers;

import com.example.bankingservice.dto.CurrencyUsdDto;
import com.example.bankingservice.dto.ExchangeResponse;
import com.example.bankingservice.models.CurrencyUsd;
import com.example.bankingservice.services.CurrencyUsdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/exchange")
public class CurrencyController {

    private final CurrencyUsdService currencyService;

    private final ModelMapper modelMapper;


    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyController(CurrencyUsdService currencyService, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    // получаем данные с биржи
    @GetMapping("/api")
    public ResponseEntity<HttpStatus> receiving() throws JsonProcessingException {

        RestTemplate restTemplate=new RestTemplate();
        String url="https://api.twelvedata.com/time_series?symbol=USD/KZT&interval=1day&apikey=f4a116dea9a246729d0b0f6bebfea33a";
        ExchangeResponse response=restTemplate.getForObject(url,  ExchangeResponse.class);

        System.out.println(response.getValues().get(0).getClose());
        currencyService.save(convertToCurrencyUsd(response.getValues().get(0)));

        //отправляем HTTP ответ с пустым и со стастусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // получем список всех данных
    @GetMapping("/list-currency")
    @ResponseBody
    public List<CurrencyUsdDto> getTransaction(){
        return currencyService.findAll().stream().map(this::convertToCurrencyUsdDto).collect(Collectors.toList());//JACKSON КОНВЕНТИРУЕТ ЭТИ ОБЬЕКТЫ В JSON
    }

    private CurrencyUsd convertToCurrencyUsd(CurrencyUsdDto currencyDto) {
        return modelMapper.map(currencyDto, CurrencyUsd.class);
    }

    private CurrencyUsdDto convertToCurrencyUsdDto(CurrencyUsd currencyUsd) {
        return modelMapper.map(currencyUsd, CurrencyUsdDto.class);
    }
}
