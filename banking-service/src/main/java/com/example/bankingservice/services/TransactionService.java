package com.example.bankingservice.services;

import com.example.bankingservice.models.Limit;
import com.example.bankingservice.models.Transaction;
import com.example.bankingservice.repositories.LimitRepsoitory;
import com.example.bankingservice.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final LimitRepsoitory limitRepsoitory;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, LimitRepsoitory limitRepsoitory) {
        this.transactionRepository = transactionRepository;
        this.limitRepsoitory = limitRepsoitory;
    }

    public List<Transaction> findByLimitExceeded(int id){
        return transactionRepository.findByLimitExceeded(id);
    }

    public Optional<Transaction> findById(Long id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> findByClientId(int id){
        return transactionRepository.getInfoByClientId(id);
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    @Transactional
    public void saveTran(Transaction transaction){
        enrichTransaction(transaction);
        transactionRepository.save(transaction);
    }

    private void enrichTransaction(Transaction transaction){
        transaction.setOperation_time(LocalDateTime.now());
        transaction.setClient_id(1);
        // после авторизацийи в системе, мы можем получить айди клиента через Principle, потом сохраним этот айди в базе
        // пока для всех киентов ставим айди дефолт как пример
        Limit limit=limitRepsoitory.getLimitByParams(transaction.getClient_id(), transaction.getType_operation(),transaction.getCurrency());

        if(limit.getLimit_sum()<transaction.getSum()){
            transaction.setLimit_exceed(true);
        }else{
            transaction.setLimit_exceed(false);
        }

        double amount=(limit.getLimit_sum()-transaction.getSum());
        transaction.setLimit_exceed_sum(amount);
        limitRepsoitory.changeLimitByTran(transaction.getClient_id(), transaction.getType_operation(),transaction.getCurrency(), amount);
        // очень много лишнего кода, потом изменю и улучшу код
    }
}
