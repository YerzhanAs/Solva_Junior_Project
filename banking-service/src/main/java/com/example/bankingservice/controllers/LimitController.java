package com.example.bankingservice.controllers;

import com.example.bankingservice.dto.LimitDto;
import com.example.bankingservice.dto.TransactionDto;
import com.example.bankingservice.models.Limit;
import com.example.bankingservice.models.Transaction;
import com.example.bankingservice.services.LimitService;
import com.example.bankingservice.services.TransactionService;
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
@RequestMapping("/currency-limit")
public class LimitController {


    private final RestTemplate restTemplate;

    private final LimitService limitService;

    private final ModelMapper modelMapper;

    @Autowired
    public LimitController(RestTemplate restTemplate, LimitService limitService, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.limitService = limitService;
        this.modelMapper = modelMapper;
    }


    // для создание нового лимита
    @PostMapping("/set")
    public ResponseEntity<HttpStatus> addLimitCurrency(@RequestBody LimitDto limitDto){

        limitService.createLimit(convertToLimit(limitDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // чтобы изменить лимит, не закончено
    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateLimitCurrency(@RequestBody int id){

        limitService.updateLimit(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // все лимиты по айди клиента (KZT, USD, EUR)
    @GetMapping("/all-limit-client/{id}")
    @ResponseBody
    public List<LimitDto> findByClientId(@PathVariable(value="id") int id){
        return limitService.findByClientId(id).stream().map(this::convertToLimitDTO).collect(Collectors.toList());
    }

    private Limit convertToLimit(LimitDto limitDto) {
        return modelMapper.map(limitDto, Limit.class);
    }

    private LimitDto convertToLimitDTO(Limit limit){
        return modelMapper.map(limit, LimitDto.class);
    }
}
