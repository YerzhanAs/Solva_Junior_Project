package com.example.bankingservice.controllers;

import com.example.bankingservice.dto.LimitDto;
import com.example.bankingservice.dto.TransactionDto;
import com.example.bankingservice.models.Transaction;
import com.example.bankingservice.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transaction")
public class TransactionController {


    private final RestTemplate restTemplate;

    private final TransactionService transactionService;

    private final ModelMapper modelMapper;

    @Autowired
    public TransactionController(RestTemplate restTemplate, TransactionService transactionService, ModelMapper modelMapper) {
        this.restTemplate = restTemplate;
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    // выводим список где limit-exceeded=true
    @GetMapping("/list-limit-exceeded/{id}")
    @ResponseBody
    public List<TransactionDto> findLimitExceeded(@PathVariable(value="id") int id){
        return transactionService.findByLimitExceeded(id).stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
    }

    // выводим данные через айди транзакций
    @GetMapping("/transaction-list/{id}")
    @ResponseBody
    public Optional<Transaction> showTranList(@PathVariable(value="id") Long id){

        return transactionService.findById(id);
    }

    // получаем все транзакций в базе, будущем надо ограничить доступ с помощью @Secured
    @GetMapping("/findAll")
    @ResponseBody
    public List<TransactionDto> getTransaction(){
        return transactionService.findAll().stream().map(this::convertToTransactionDTO).collect(Collectors.toList());//JACKSON КОНВЕНТИРУЕТ ЭТИ ОБЬЕКТЫ В JSON
    }

    //получем все транзакций с помощью айди клиента
    @GetMapping("/findByClient/{id}")
    @ResponseBody
    public List<TransactionDto> findByClientId(@PathVariable(value="id") int id){
        return transactionService.findByClientId(id).stream().map(this::convertToTransactionDTO).collect(Collectors.toList());
    }

    //сохрнять транзакцию в базе
    @PostMapping("/save")
    public ResponseEntity<HttpStatus> saveTranFromClient(@RequestBody TransactionDto transactionDTO){

        transactionService.saveTran(convertToTransaction(transactionDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Transaction convertToTransaction(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

    private TransactionDto convertToTransactionDTO(Transaction transaction){
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
