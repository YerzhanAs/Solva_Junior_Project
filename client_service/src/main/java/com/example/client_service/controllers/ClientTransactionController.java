package com.example.client_service.controllers;


import com.example.client_service.models.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/bank-operation")
//client/
public class ClientTransactionController {

    @GetMapping("/sent-page")
    public String senderPage(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction", transaction);
        return "sent-transaction";
    }

    // здесь мы отправлем данные полученные с фронты в сервис банка
    @PostMapping("/sent-page")
    public Transaction tranToBank(@ModelAttribute(value="transaction") Transaction transaction){
        transaction.setId((long) (Math.random() * 1000));
        // После регистраций, можно получить сlient_id  через Principle.Потом полученный айди скидываем в базу
        transaction.setClient_id(1L);
        transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }

    @GetMapping("/sent")
    @ResponseBody
    public Transaction tranToBank(){
        Transaction transaction1=new Transaction();
        transaction1.setAccount_from(5555);
        transaction1.setAccount_to(4444);
        transaction1.setCurrency_shortname("USD");
        transaction1.setExpense_category("product");
        transaction1.setId((long) (Math.random() * 1000));
        // После регистраций, можно получить сlient_id  через Principle.Потом полученный айди скидываем в базу
        transaction1.setClient_id(1L);
        transaction1.setCreatedAt(LocalDateTime.now());
        System.out.println(transaction1.toString());
        return transaction1;
    }



}
