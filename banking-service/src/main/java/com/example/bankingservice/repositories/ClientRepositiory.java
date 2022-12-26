package com.example.bankingservice.repositories;

import com.example.bankingservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositiory extends JpaRepository<Client, Integer> {
}
