package com.example.bankingservice.models;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(name="account_from")
    private int account_from;

    @Column(name="account_to")
    private int account_to;

    @Column(name="sum")
    private double sum;

    @Column(name="limit_exceed_sum")
    private double limit_exceed_sum;

    @Column(name="type_operation")
    private String type_operation;

    @Column(name="currency")
    private String currency;

    @Column(name="operation_time")
    private LocalDateTime operation_time;

    @Column(name="limit_exceed")
    private Boolean limit_exceed;

    @Column(name="client_id")
    private int client_id;

}
