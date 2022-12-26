package com.example.bankingservice.dto;


import java.util.Currency;

public class LimitDto {

    private String type_op;

    private double limit_sum;

    private String currency;

    private String period;

    public String getType_op() {
        return type_op;
    }

    public void setType_op(String type_op) {
        this.type_op = type_op;
    }

    public double getLimit_sum() {
        return limit_sum;
    }

    public void setLimit_sum(double limit_sum) {
        this.limit_sum = limit_sum;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
