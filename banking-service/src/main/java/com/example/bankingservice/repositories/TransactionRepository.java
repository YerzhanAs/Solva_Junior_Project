package com.example.bankingservice.repositories;

import com.example.bankingservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.client_id= :client_id")
    List<Transaction> getInfoByClientId(@Param("client_id") int id);

    @Query("Select t FROM Transaction t  where t.limit_exceed= true and t.client_id= :client_id")
    List<Transaction> findByLimitExceeded(@Param("client_id") int id);
}
