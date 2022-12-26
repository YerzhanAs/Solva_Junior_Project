package com.example.bankingservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="tran_limit")
public class Limit {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type_op")
    private String type_op;

    @Column(name="limit_sum")
    private double limit_sum;

    @Column(name="createdat")
    private LocalDateTime createdAt;

    @Column(name="updatedat")
    private LocalDateTime updatedAt;

    @Column(name="currency")
    private String currency;

    @Column(name="period")
    private String period;

    @Column(name="client_id")
    private int client_id;

}
