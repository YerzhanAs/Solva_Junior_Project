package com.example.bankingservice.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="Currencyusd")
public class CurrencyUsd {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="closeusd")
    private BigDecimal close;

    @Column(name="datetime")
    @NotEmpty(message = "Date should not be empty")
    private String datetime;

    @Column(name="created_at")
    private LocalDateTime createdAt;


}
