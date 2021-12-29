/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.TransactionHistory;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gharibaahmedsuleiman
 */
@Repository
public interface TransactRepository extends JpaRepository<TransactionHistory, Long>{
    
     @Modifying
    @Transactional
    @Query(value = "INSERT INTO transaction_history(account_id, transaction_type, amount, status, reason_code, created_at)" +
            "VALUES(:account_id, :transact_type, :amount, :status, :reason_code, :created_at)", nativeQuery = true)
    void logTransaction(@Param("account_id")long account_id,//transferfromid
                        @Param("transact_type")String transact_type,//transaction type
                        @Param("amount")double amount, //amount
                        @Param("status")String status, //failed , succeeded
                        @Param("reason_code")String reason_code, // insufficient funds...
                        @Param("created_at") LocalDateTime created_at); //time
}
