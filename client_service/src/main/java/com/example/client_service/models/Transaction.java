package com.example.client_service.models;




import java.math.BigDecimal;
import java.time.LocalDateTime;



public class Transaction {

    private Long id;


    private int account_from;


    private int account_to;


    private String currency_shortname;


    private BigDecimal sum;

    private String expense_category;

    private LocalDateTime createdAt;

    private Long client_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public String getCurrency_shortname() {
        return currency_shortname;
    }

    public void setCurrency_shortname(String currency_shortname) {
        this.currency_shortname = currency_shortname;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getExpense_category() {
        return expense_category;
    }

    public void setExpense_category(String expense_category) {
        this.expense_category = expense_category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account_from=" + account_from +
                ", account_to=" + account_to +
                ", currency_shortname='" + currency_shortname + '\'' +
                ", sum=" + sum +
                ", expense_category='" + expense_category + '\'' +
                ", createdAt=" + createdAt +
                ", client_id=" + client_id +
                '}';
    }
}
