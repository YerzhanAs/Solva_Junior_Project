package com.example.bankingservice.repositories;

import com.example.bankingservice.models.CurrencyUsd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyUsdRepository extends JpaRepository<CurrencyUsd, Integer> {
}
