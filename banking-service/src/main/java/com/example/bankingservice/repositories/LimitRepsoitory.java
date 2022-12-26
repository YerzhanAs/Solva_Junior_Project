package com.example.bankingservice.repositories;

import com.example.bankingservice.models.Limit;
import com.example.bankingservice.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepsoitory extends JpaRepository<Limit, Integer> {

    @Query("SELECT l FROM Limit l WHERE l.client_id= :client_id")
    List<Limit> getInfoByClientId(@Param("client_id") int id);

    @Query("SELECT l FROM Limit l WHERE l.client_id= :client_id and l.currency= :currency and l.type_op=:type")
    Limit getLimitByParams(@Param("client_id") int client_id,
                                 @Param("type") String type,
                                 @Param("currency") String currency);

    @Modifying
    @Query(value ="UPDATE Limit l SET l.limit_sum= :limit_sum  WHERE l.client_id= :client_id and l.currency= :currency and l.type_op=:type")
    void changeLimitByTran(@Param("client_id") int client_id,
                           @Param("type") String type,
                            @Param("currency") String currency,
                           @Param("limit_sum") double limit_sum);

}
