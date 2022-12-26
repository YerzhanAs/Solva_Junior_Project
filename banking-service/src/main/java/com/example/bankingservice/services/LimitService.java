package com.example.bankingservice.services;

import com.example.bankingservice.models.Limit;
import com.example.bankingservice.models.Transaction;
import com.example.bankingservice.repositories.LimitRepsoitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LimitService {


    private final LimitRepsoitory limitRepsoitory;

    @Autowired
    public LimitService(LimitRepsoitory limitRepsoitory) {
        this.limitRepsoitory = limitRepsoitory;
    }

    public List<Limit> findByClientId(int id){
        return limitRepsoitory.getInfoByClientId(id);
    }


    // не реализована пока
    public void updateLimit(int id){

    }


    @Transactional
    public void createLimit(Limit limit){
        enrichLimit(limit);
        limitRepsoitory.save(limit);
    }


    private void enrichLimit(Limit limit){
        limit.setCreatedAt(LocalDateTime.now());
        limit.setUpdatedAt(LocalDateTime.now());
        limit.setClient_id(1);
        // после авторизацийи в системе, мы можем получить айди клиента через Principle, потом сохраним этот айди в базе
        // пока для всех киентов ставим айди дефолт как пример
    }
}
